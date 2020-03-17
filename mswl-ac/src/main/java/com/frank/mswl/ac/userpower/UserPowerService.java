package com.frank.mswl.ac.userpower;

import com.alibaba.fastjson.JSONObject;
import com.frank.mswl.ac.function.FunctionRepository;
import com.frank.mswl.ac.function.FunctionResponse;
import com.frank.mswl.ac.menu.MenuRepository;
import com.frank.mswl.ac.menu.MenuResponse;
import com.frank.mswl.ac.user.UserRepository;
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
 * web端用户权限操作service.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */

@Service
public class UserPowerService {

    @Autowired
    private UserPowerRepository userPowerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private FunctionRepository functionRepository;

    /**
     * 用户权限查询接口实现.
     *
     * @return Json字符串数据
     */
    public String userPowerQuery(WebRequest<UserPowerRequest> requestData) {
        // 获取报文体对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        map.put("userid", (String) userRepository.userQuery(requestData.getRequest().getUserid()).get("id"));
        // 获取有权限的菜单
        List<Map<String, Object>> userPower = userPowerRepository.userPowerQuery(map);
        // 获取整个菜单列表
        List<Map<String, Object>> menuList = menuRepository.menuQueryLists(new HashMap());
        for (int i = 0; i < menuList.size(); i++) {
            HashMap menu = (HashMap) menuList.get(i);
            menu.put("power", "0");
        }
        // 循环并标记那些是和当前用户有权限的菜单
        for (int i = 0; i < userPower.size(); i++) {
            for (int ii = 0; ii < menuList.size(); ii++) {
                HashMap menu = (HashMap) menuList.get(ii);
                if (userPower.get(i).get("menuid").equals(menu.get("menuid"))) {
                    menu.put("power", (String) userPower.get(i).get("power"));
                }
            }
        }
        // 构建机构树
        HashMap<String, Object> menulist = new HashMap<String, Object>();
        this.getJgTree(menuList, "", menulist);
        //创建接收对象
        WebResponse<MenuResponse> web = new WebResponse<>();
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setMenulist(menulist.get("menulist"));
        web.setResponse(menuResponse);
        return JSONObject.toJSON(web).toString();
    }

    /**
     * 用户权限修改接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String userPowerUpdate(WebRequest<UserPowerRequest> requestData) {
        // 获取报文体对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        String userid = (String) userRepository.userQuery(requestData.getRequest().getUserid()).get("id");
        userPowerRepository.userPowerDelete(userid);
        String[] menuidlist = requestData.getRequest().getMenuidlist();
        map.put("userid", userid);
        map.put("operator", requestData.getUserid());
        for (int i = 0; i < menuidlist.length; i++) {
            map.put("addTime", DateTimeUtil.getTimeformat());
            map.put("uuid", CommonUtil.getUUid());
            map.put("menuid", menuidlist[i]);
            userPowerRepository.userPowerAdd(map);
        }
        return new SysResponse().toJsonString();
    }

    /**
     * 用户按钮权限菜单查询接口实现.
     *
     * @return Json字符串数据
     */
    public String userfunctionQuery(WebRequest<UserPowerRequest> requestData) {
        //获取已有权限的菜单列表
        List<Map<String, Object>> lstData = menuRepository.userfunctionQuerys((String) userRepository.userQuery(requestData.getRequest().getUserid()).get("id"));
        // 构建机构树
        HashMap<String, Object> menulist = new HashMap<String, Object>();
        this.getJgTree(lstData, "", menulist);
        //创建接收对象
        WebResponse<MenuResponse> web = new WebResponse<>();
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setMenulist(menulist.get("menulist"));
        web.setResponse(menuResponse);
        return JSONObject.toJSON(web).toString();
    }

    /**
     * 用户按钮权限查询.
     *
     * @return Json字符串数据
     */
    public String userPowerfunctionQuery(WebRequest<UserPowerRequest> requestData) {
        // 获取报文体对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        map.put("userid", (String) userRepository.userQuery(requestData.getRequest().getUserid()).get("id"));
        // 获取当前菜单所有的功能按钮
        List<Map<String, FunctionResponse.Function>> functionList = functionRepository.functionQuery(map);
        // 获取有权限的菜单
        List<Map<String, Object>> userPowerFunction = userPowerRepository.userPowerFunctionQuery(map);
        for (int i = 0; i < userPowerFunction.size(); i++) {
            for (int ii = 0; ii < functionList.size(); ii++) {
                HashMap upf = (HashMap) functionList.get(ii);
                if (userPowerFunction.get(i).get("functionid").equals(upf.get("functionid"))) {
                    upf.put("ftion", "1");
                } else {
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
     * 用户权限功能按钮修改接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String userfunctionUpdate(WebRequest<UserPowerRequest> requestData) {
        // 获取报文体对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        String menuid = requestData.getRequest().getParentmenuid();
        String userid = (String) userRepository.userQuery(requestData.getRequest().getUserid()).get("id");
        userPowerRepository.userPowerFunctionDelete(menuid,userid);
        String[] functionidlist = requestData.getRequest().getFunctionidlist();
        map.put("userid", userid);
        map.put("menuid", requestData.getRequest().getParentmenuid());
        map.put("operator", requestData.getUserid());
        for (int i = 0; i < functionidlist.length; i++) {
            map.put("addTime", DateTimeUtil.getTimeformat());
            map.put("uuid", CommonUtil.getUUid());
            map.put("functionid", functionidlist[i]);
            userPowerRepository.userfunctionAdd(map);
        }
        return new SysResponse().toJsonString();
    }

    /**
     * 用户权限功能按钮修改接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String userfunctionUpdates(String userid,String operator,String menuid,List<Map> functionidlist) {
        // 获取报文体对象转换成map集合 并给dao传值
        HashMap map = new HashMap();
        userPowerRepository.userPowerFunctionDelete(menuid,userid);
        map.put("userid", userid);
        map.put("menuid",menuid);
        map.put("operator",operator);
        for (int i = 0; i < functionidlist.size(); i++) {
            map.put("addTime", DateTimeUtil.getTimeformat());
            map.put("uuid", CommonUtil.getUUid());
            map.put("functionid",functionidlist.get(i).get("functionid"));
            userPowerRepository.userfunctionAdd(map);
        }
        return new SysResponse().toJsonString();
    }

    /**
     * 构建机构树.
     *
     * @param lstData 原始数据.
     * @param pid     上级节点ID.
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

    /**
     * 用户功能查询接口实现.
     *
     * @return Json字符串数据
     */
    public String userfunctionquerys(WebRequest<UserPowerRequest> requestData) {
        //获取报文体
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        WebResponse<UserPowerResponse> web = new WebResponse<>();
        UserPowerResponse userPowerResponse = new UserPowerResponse();
        List<FunctionResponse> reList = new ArrayList<>();
        List<String> list = requestData.getRequest().getMenuid();
        for (int i = 0; i < list.size(); i++) {
            map.put("menuid", list.get(i));
            if (!requestData.getRequest().getUserid().equals("")) {
                map.put("userid", (String) userRepository.userQuery(requestData.getRequest().getUserid()).get("id"));
                // 获取当前菜单所有的功能按钮
                List<Map<String, FunctionResponse.Function>> functionList = userPowerRepository.userfunctionQuerys(map);
                for (int j = 0; j < functionList.size(); j++) {
                    HashMap function = (HashMap) functionList.get(j);
                    function.put("power", "0");
                }
                // 获取有权限的按钮
                List<Map<String, Object>> userPowerFunction = userPowerRepository.userPowerFunctionQuery(map);
                for (int k = 0; k < userPowerFunction.size(); k++) {
                    for (int ii = 0; ii < functionList.size(); ii++) {
                        HashMap upf = (HashMap) functionList.get(ii);
                        if (userPowerFunction.get(k).get("functionid").equals(upf.get("functionid"))) {
                            upf.put("power", "1");
                        }
                    }
                }
                FunctionResponse functionResponse = new FunctionResponse();
                functionResponse.setFunctionlist(functionList);
                functionResponse.setMenuid(list.get(i));
                reList.add(functionResponse);
            }
        }
        userPowerResponse.setUsermenulist(reList);
        web.setResponse(userPowerResponse);
        return JSONObject.toJSON(web).toString();
    }
}
