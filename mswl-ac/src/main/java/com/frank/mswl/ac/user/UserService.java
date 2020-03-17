package com.frank.mswl.ac.user;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frank.mswl.ac.function.FunctionRepository;
import com.frank.mswl.ac.rolepower.RolePowerRepository;
import com.frank.mswl.ac.userpower.UserPowerRepository;
import com.frank.mswl.ac.userpower.UserPowerService;
import com.frank.mswl.core.request.WebRequest;
import com.frank.mswl.core.response.SysErrResponse;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.Md5Util;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.DateTimeUtil;
import com.frank.mswl.core.utils.SdyfJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端用户信息操作service.
 *
 * @author 张明亮 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/08 张明亮 创建.
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolePowerRepository rolePowerRepository;

    @Autowired
    private UserPowerRepository userPowerRepository;

    @Autowired
    private FunctionRepository functionRepository;

    @Autowired
    private UserPowerService userPowerService;

    /**
     * 用户添加接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String userAdd(WebRequest<UserRequest> requestData) {
        //对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        int Only = userRepository.userOnly(requestData.getRequest().getUserid());
        if (Only == 0) {
            //定义用户id
            String userId = CommonUtil.getUUid();
            map.put("operator", requestData.getUserid());
            //添加用户给其默认密码123456  md5加密
            map.put("passwd", Md5Util.MD5Encode("123456", "utf8"));
            map.put("usid", userId);
            map.put("addTime", DateTimeUtil.getTimeformat());
            map.put("uuid", CommonUtil.getUUid());
            //添加用户信息
            userRepository.userAdd(map);
            //添加用户和机构关联信息
            userRepository.userOrganAdd(map);
            //定义角色，分割后循环添加用户和角色的关联信息
            String[] rolelist = requestData.getRequest().getRolelist();
            for (int i = 0; i < rolelist.length; i++) {
                map.put("uuid", CommonUtil.getUUid());
                map.put("role", rolelist[i]);
                userRepository.userRoleAdd(map);
            }
            // 获取有权限的菜单 并添加权限
            List<Map<String, Object>> rolePower = rolePowerRepository.rolePowerQuerys(rolelist);
            for (int i = 0; i < rolePower.size(); i++) {
                map.put("userid", userId);
                map.put("addTime", DateTimeUtil.getTimeformat());
                map.put("uuid", CommonUtil.getUUid());
                map.put("menuid", rolePower.get(i).get("menuid"));
                map.put("roleid", rolePower.get(i).get("roleid"));
                userPowerRepository.userPowerAdd(map);
                // 查询功能权限并修改
                List<Map> function = functionRepository.menuFunctionQuery((String) map.get("menuid"), (String) map.get("roleid"));
                if (function.size() != 0) {
                    for (int j = 0; j < function.size(); j++) {
                        userPowerService.userfunctionUpdates((String) map.get("userid"), requestData.getUserid(), (String) map.get("menuid"), function);
                    }
                }
            }
            return new SysResponse().toJsonString();
        } else {
            return new SysErrResponse("用户登录名已存在，请修改后重试！").toJsonString();
        }
    }

    /**
     * 用户删除接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String userDelete(WebRequest<UserRequest> requestData) {
        //接收需要删除的用户数组字符串
        String[] Userid = requestData.getRequest().getUseridlist();
        for (int i = 0; i < Userid.length; i++) {
            //获取用户id
            String userId = (String) userRepository.userQuery(Userid[i]).get("id");
            //删除用户信息
            userRepository.userDelete(userId);
            //删除用户和机构关联信息
            userRepository.userOrganDelete(userId);
            //删除用户和角色关联信息
            userRepository.userRoleDelete(userId);
        }
        return new SysResponse().toJsonString();
    }

    /**
     * 用户修改接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String userUpdate(WebRequest<UserRequest> requestData) {
        //获取用户id
        String userId = (String) userRepository.userQuery(requestData.getRequest().getUserid()).get("id");
        //对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        map.put("updateTime", DateTimeUtil.getTimeformat());
        map.put("operator", requestData.getUserid());
        map.put("addTime", DateTimeUtil.getTimeformat());
        map.put("usid", userId);
        //修改用户信息
        userRepository.userUpdate(map);
        //修改用户和机构关联信息
        userRepository.userOrganUpdate(map);
        //定义角色，分割后循环添加用户和角色的关联信息
        String[] rolelist = requestData.getRequest().getRolelist();
        if (rolelist.length > 0) {
            //删除用户和角色关联信息
            userRepository.userRoleDelete(userId);
            userRepository.userFunctionDelete(userId);
            for (int i = 0; i < rolelist.length; i++) {
                map.put("uuid", CommonUtil.getUUid());
                map.put("role", rolelist[i]);
                userRepository.userRoleAdd(map);
            }
            // 清除已有的权限
            userPowerRepository.userPowerDelete(userId);
            // 获取有权限的菜单 并修改权限
            List<Map<String, Object>> rolePower = rolePowerRepository.rolePowerQuerys(rolelist);
            for (int i = 0; i < rolePower.size(); i++) {
                map.put("userid", userId);
                map.put("addTime", DateTimeUtil.getTimeformat());
                map.put("uuid", CommonUtil.getUUid());
                map.put("menuid", rolePower.get(i).get("menuid"));
                map.put("roleid", rolePower.get(i).get("roleid"));
                userPowerRepository.userPowerAdd(map);
                // 查询功能权限并修改
                List<Map> function = functionRepository.menuFunctionQuery((String) map.get("menuid"), (String) map.get("roleid"));
                if (function.size() != 0) {
                    for (int j = 0; j < function.size(); j++) {
                        userPowerService.userfunctionUpdates((String) map.get("userid"), requestData.getUserid(), (String) map.get("menuid"), function);
                    }
                }

            }
        }
        return new SysResponse().toJsonString();
    }

    /**
     * 用户查询接口实现.
     *
     * @return Json字符串数据
     */
    public String userQueryList(WebRequest<UserRequest> requestData) {
        //获取报文体
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        if (requestData.getRequest().getStartindex() != null && !"".equals(requestData.getRequest().getStartindex()) && requestData.getRequest().getPagesize() != null && !"".equals(requestData.getRequest().getPagesize())) {
            //为防止分页查询出错，把这俩个属性强转为int类型
            map.put("startindex", Integer.parseInt(requestData.getRequest().getStartindex()));
            map.put("pagesize", Integer.parseInt(requestData.getRequest().getPagesize()));
            map.put("pagingOrNot", "1");
        }
        WebResponse<UserResponse> web = new WebResponse<>();
        UserResponse userResponse = new UserResponse();
        userResponse.setUserlist(userRepository.userQueryList(map));
        userResponse.setDraw(requestData.getRequest().getDraw());
        userResponse.setTotalcount(String.valueOf(userRepository.number(map)));
        web.setResponse(userResponse);
        return JSONObject.toJSON(web).toString();
    }

    /**
     * 密码重置接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String passWordRest(WebRequest<UserRequest> requestData) {
        //获取userid数组
        String[] Userid = requestData.getRequest().getUseridlist();
        for (int i = 0; i < Userid.length; i++) {
            //获取报文体并转换成map集合 方便给dao传值
            HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
            map.put("updateTime", DateTimeUtil.getTimeformat());
            map.put("operator", requestData.getUserid());
            map.put("userid", Userid[i]);
            userRepository.passWordRest(map);
        }
        return new SysResponse().toJsonString();
    }

    /**
     * 密码修改接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String passWordChange(WebRequest<UserRequest> requestData) {
        //获取报文体并转换成map集合 方便给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        //查询原密码 进行对比
        String passwd = (String) userRepository.userQuery((String) requestData.getUserid()).get("passwd");
        if (passwd.equals(Md5Util.MD5Encode((String) map.get("oldpassword"), "utf8"))) {
            if (passwd.equals(Md5Util.MD5Encode((String) map.get("newpassword"), "utf8"))) {
                return new SysErrResponse("新老密码相同，无需修改").toJsonString();
            }
            map.put("updateTime", DateTimeUtil.getTimeformat());
            map.put("operator", requestData.getUserid());
            map.put("password", Md5Util.MD5Encode((String) map.get("newpassword"), "utf8"));
            map.put("userid", requestData.getUserid());
            userRepository.passWordRest(map);
            return new SysResponse().toJsonString();
        } else {
            return new SysErrResponse("原密码输入错误").toJsonString();
        }
    }
}
