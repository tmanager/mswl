package com.frank.mswl.ac.rolepower;

import com.alibaba.fastjson.JSONObject;
import com.frank.mswl.ac.user.UserRepository;
import com.frank.mswl.ac.function.FunctionRepository;
import com.frank.mswl.ac.function.FunctionResponse;
import com.frank.mswl.ac.menu.MenuRepository;
import com.frank.mswl.ac.menu.MenuResponse;
import com.frank.mswl.ac.userpower.UserPowerRepository;
import com.frank.mswl.ac.userpower.UserPowerService;
import com.frank.mswl.core.request.WebRequest;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.DateTimeUtil;
import com.frank.mswl.core.utils.SdyfJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端角色权限操作service.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */

@Service
public class RolePowerService {

    @Autowired
    private RolePowerRepository rolePowerRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private FunctionRepository functionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPowerRepository userPowerRepository;

    @Autowired
    private UserPowerService userPowerService;

    /**
     * 角色权限查询接口实现.
     *
     * @return Json字符串数据
     */
    public String rolepowerQuery(WebRequest<RolePowerRequest> requestData) {
        // 获取报文体对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        // 获取有权限的菜单
        List<Map<String, Object>> rolePower = rolePowerRepository.rolePowerQuery(map);
        // 获取整个菜单列表
        List<Map<String, Object>> menuList = menuRepository.menuQueryLists(new HashMap());
        for (int i = 0; i < menuList.size(); i++) {
            HashMap menu = (HashMap) menuList.get(i);
            menu.put("power","0");
        }
        // 循环并标记那些是和当前用户有权限的菜单
        for (int i = 0; i < rolePower.size(); i++) {
            for (int ii = 0; ii < menuList.size(); ii++) {
                HashMap menu = (HashMap) menuList.get(ii);
                if (rolePower.get(i).get("menuid").equals(menu.get("menuid"))) {
                    menu.put("power",(String) rolePower.get(i).get("power"));
                }
            }
        }
        // 构建机构树
        HashMap<String, Object> menulist = new HashMap<String, Object>();
        this.getJgTree(menuList,"", menulist);
        //创建接收对象
        WebResponse<MenuResponse> web = new WebResponse<>();
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setMenulist(menulist.get("menulist"));
        web.setResponse(menuResponse);
        return JSONObject.toJSON(web).toString();
    }

    /**
     * 角色权限修改接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String rolepowerUpdate(WebRequest<RolePowerRequest> requestData) {
        // 获取报文体对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        // 先删除老的权限
        rolePowerRepository.rolePowerDelete(requestData.getRequest().getRoleid());
        String[] menuidlist = requestData.getRequest().getMenuidlist();
        map.put("roleid",requestData.getRequest().getRoleid());
        map.put("operator", requestData.getUserid());
        for (int i = 0; i < menuidlist.length; i++) {
            map.put("addTime", DateTimeUtil.getTimeformat());
            map.put("uuid", CommonUtil.getUUid());
            map.put("menuid", menuidlist[i]);
            rolePowerRepository.rolePowerAdd(map);
        }
        List<Map<String, String>> rolegetuser=rolePowerRepository.rolegetuser(map);
        if (rolegetuser.size()!=0){
            for (int i = 0; i < rolegetuser.size(); i++) {
                HashMap maps=new HashMap();
                maps.put("updateTime", DateTimeUtil.getTimeformat());
                maps.put("operator", requestData.getUserid());
                maps.put("addTime", DateTimeUtil.getTimeformat());
                maps.put("usid", (String) rolegetuser.get(i).get("userid"));
                List<Map<String, String>> rolenum=rolePowerRepository.usergetrolenum((String) rolegetuser.get(i).get("userid"));
                userRepository.userRoleDelete((String) rolegetuser.get(i).get("userid"));
                userRepository.userFunctionDelete((String) rolegetuser.get(i).get("userid"));
                // 清除已有的权限
                userPowerRepository.userPowerDelete((String) rolegetuser.get(i).get("userid"));
                for (int j = 0; j <rolenum.size(); j++) {
                    maps.put("uuid", CommonUtil.getUUid());
                    maps.put("role", rolenum.get(j).get("roleid"));
                    userRepository.userRoleAdd(maps);
                }
                String[] rolelist=new String[rolenum.size()];
                for (int j = 0; j <rolenum.size() ; j++) {
                    rolelist[j] = (String) rolenum.get(j).get("roleid");
                }
                // 获取有权限的菜单 并修改权限
                List<Map<String, Object>> rolePower = rolePowerRepository.rolePowerQuerys(rolelist);
                for (int j = 0; j <rolePower.size() ; j++) {
                    maps.put("userid",(String) rolegetuser.get(i).get("userid"));
                    maps.put("addTime", DateTimeUtil.getTimeformat());
                    maps.put("uuid", CommonUtil.getUUid());
                    maps.put("menuid", rolePower.get(j).get("menuid"));
                    maps.put("roleid", rolePower.get(j).get("roleid"));
                    userPowerRepository.userPowerAdd(maps);
                    // 查询功能权限并修改
                    List<Map> functions=functionRepository.menuFunctionQuery((String)rolePower.get(j).get("menuid"),(String)rolePower.get(j).get("roleid"));
                    if (functions.size()!=0){
                        for (int k = 0; k < functions.size(); k++) {
                            userPowerService.userfunctionUpdates((String)maps.get("userid"),requestData.getUserid(),(String)maps.get("menuid"),functions);
                        }
                    }
                }
            }
        }
        return new SysResponse().toJsonString();
    }

    /**
     * 角色按钮权限菜单查询接口实现.
     *
     * @return Json字符串数据
     */
    public String rolefunctionQuery(WebRequest<RolePowerRequest> requestData) {
        //获取已有权限的菜单列表
        List<Map<String, Object>> lstData= menuRepository.rolefunctionQuerys(requestData.getRequest().getRoleid());
        // 构建机构树
        HashMap<String, Object> menulist = new HashMap<String, Object>();
        this.getJgTree(lstData,"", menulist);
        //创建接收对象
        WebResponse<MenuResponse> web = new WebResponse<>();
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setMenulist(menulist.get("menulist"));
        web.setResponse(menuResponse);
        return JSONObject.toJSON(web).toString();
    }

    /**
     * 角色按钮权限查询.
     *
     * @return Json字符串数据
     */
    public String rolePowerfunctionQuery(WebRequest<RolePowerRequest> requestData) {
         //获取报文体对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        // 获取当前菜单所有的功能按钮
        List<Map<String, FunctionResponse.Function>> functionList = functionRepository.functionQuery(map);
        // 获取有权限的菜单
        List<Map<String, Object>> rolePowerFunction = rolePowerRepository.rolePowerFunctionQuery(map);
        for (int i = 0; i <rolePowerFunction.size() ; i++) {
            for (int ii = 0; ii <functionList.size() ; ii++) {
                HashMap upf= (HashMap) functionList.get(ii);
                if (rolePowerFunction.get(i).get("functionid").equals(upf.get("functionid"))){
                    upf.put("ftion","1");
                }else{
                    continue;
                }
            }
        }
        //创建接收对象
        WebResponse<FunctionResponse> web = new WebResponse<>();
        FunctionResponse functionResponse = new FunctionResponse();
        functionResponse.setMenuid(requestData.getRequest().getParentmenuid());
        functionResponse.setFunctionlist(functionList);
        web.setResponse(functionResponse);
        return JSONObject.toJSON(web).toString();
    }

    /**
     * 角色权限功能按钮修改接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String rolefunctionUpdate(WebRequest<RolePowerRequest> requestData) {
        // 获取报文体对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        rolePowerRepository.rolePowerFunctionDelete(requestData.getRequest().getRoleid(),requestData.getRequest().getMenuid());
        String[] functionidlist = requestData.getRequest().getFunctionidlist();
        map.put("menuid",requestData.getRequest().getMenuid());
        map.put("operator", requestData.getUserid());
        for (int i = 0; i < functionidlist.length; i++) {
            map.put("addTime", DateTimeUtil.getTimeformat());
            map.put("uuid", CommonUtil.getUUid());
            map.put("functionid", functionidlist[i]);
            rolePowerRepository.rolefunctionAdd(map);
        }
        List<Map<String, String>> rolegetuser=rolePowerRepository.rolegetuser(map);
        if (rolegetuser.size()!=0){
            for (int i = 0; i < rolegetuser.size(); i++) {
                HashMap maps=new HashMap();
                maps.put("updateTime", DateTimeUtil.getTimeformat());
                maps.put("operator", requestData.getUserid());
                maps.put("addTime", DateTimeUtil.getTimeformat());
                maps.put("usid", (String) rolegetuser.get(i).get("userid"));
                List<Map<String, String>> rolenum=rolePowerRepository.usergetrolenum((String) rolegetuser.get(i).get("userid"));
                userRepository.userRoleDelete((String) rolegetuser.get(i).get("userid"));
                userRepository.userFunctionDelete((String) rolegetuser.get(i).get("userid"));
                // 清除已有的权限
                userPowerRepository.userPowerDelete((String) rolegetuser.get(i).get("userid"));
                for (int j = 0; j <rolenum.size(); j++) {
                    maps.put("uuid", CommonUtil.getUUid());
                    maps.put("role", rolenum.get(j).get("roleid"));
                    userRepository.userRoleAdd(maps);
                }
                String[] rolelist=new String[rolenum.size()];
                for (int j = 0; j <rolenum.size() ; j++) {
                    rolelist[j] = (String) rolenum.get(j).get("roleid");
                }
                // 获取有权限的菜单 并修改权限
                List<Map<String, Object>> rolePower = rolePowerRepository.rolePowerQuerys(rolelist);
                for (int j = 0; j <rolePower.size() ; j++) {
                    maps.put("userid",(String) rolegetuser.get(i).get("userid"));
                    maps.put("addTime", DateTimeUtil.getTimeformat());
                    maps.put("uuid", CommonUtil.getUUid());
                    maps.put("menuid", rolePower.get(j).get("menuid"));
                    maps.put("roleid", rolePower.get(j).get("roleid"));
                    userPowerRepository.userPowerAdd(maps);
                    // 查询功能权限并修改
                    List<Map> functions=functionRepository.menuFunctionQuery((String)rolePower.get(j).get("menuid"),(String)rolePower.get(j).get("roleid"));
                    if (functions.size()!=0){
                        for (int k = 0; k < functions.size(); k++) {
                            userPowerService.userfunctionUpdates((String)maps.get("userid"),requestData.getUserid(),(String)maps.get("menuid"),functions);
                        }
                    }
                }
            }
        }
        return new SysResponse().toJsonString();
    }

    /**
     * 构建机构树.
     *
     * @param lstData
     *            原始数据.
     *
     * @param pid
     *            上级节点ID.
     *
     * @return 机构树.
     */
    private void getJgTree(List<Map<String, Object>> lstData, String pid, Map<String, Object> treeData) {

        List<Map<String, Object>> childrenData = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : lstData) {
            if (map.get("fjdid").toString().equals(pid)) {
                childrenData.add(map);
            }
        }

        if (childrenData.size() != 0) {
            treeData.put("menulist", childrenData);
            for (Map<String, Object> m : childrenData) {
                getJgTree(lstData, m.get("menuid").toString(), m);
            }
        }
    }

}
