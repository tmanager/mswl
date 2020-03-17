package com.frank.mswl.ac.function;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frank.mswl.ac.rolepower.RolePowerRepository;
import com.frank.mswl.ac.user.UserRepository;
import com.frank.mswl.ac.userpower.UserPowerRepository;
import com.frank.mswl.core.request.WebRequest;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
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
 * web端功能信息操作service.
 *
 * @author 张明亮 2019/06/25.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/06/25 张明亮 创建.
 */

@Service
public class FunctionService {

    @Autowired
    private FunctionRepository functionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPowerRepository userPowerRepository;

    @Autowired
    private RolePowerRepository rolePowerRepository;
    /**
     * 功能查询接口实现.
     *
     * @return Json字符串数据
     */
    public String functionQuery(WebRequest<FunctionRequest> requestData) {
        //获取报文体
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        if (requestData.getRequest().getUserid().equals("") && requestData.getRequest().getRoleid().equals("")){
            List<Map<String,FunctionResponse.Function>> functionList = functionRepository.functionQuery(map);
            //创建接收对象
            WebResponse<FunctionResponse> web = new WebResponse<>();
            FunctionResponse functionResponse = new FunctionResponse();
            functionResponse.setMenuid(requestData.getRequest().getMenuid());
            functionResponse.setFunctionlist(functionList);
            web.setResponse(functionResponse);
            return JSONObject.toJSON(web).toString();
        }else if(!requestData.getRequest().getUserid().equals("")&& requestData.getRequest().getRoleid().equals("")){
            map.put("userid",(String) userRepository.userQuery(requestData.getRequest().getUserid()).get("id"));
            // 获取当前菜单所有的功能按钮
            List<Map<String,FunctionResponse.Function>> functionList = functionRepository.functionQuery(map);
            for (int i = 0; i < functionList.size(); i++) {
                HashMap function = (HashMap) functionList.get(i);
                function.put("power","0");
            }
            // 获取有权限的菜单
            List<Map<String, Object>> userPowerFunction = userPowerRepository.userPowerFunctionQuery(map);
            for (int i = 0; i <userPowerFunction.size() ; i++) {
                for (int ii = 0; ii <functionList.size() ; ii++) {
                    HashMap upf= (HashMap) functionList.get(ii);
                    if (userPowerFunction.get(i).get("functionid").equals(upf.get("functionid"))){
                        upf.put("power","1");
                    }
                }
            }
            //创建接收对象
            WebResponse<FunctionResponse> web = new WebResponse<>();
            FunctionResponse functionResponse = new FunctionResponse();
            functionResponse.setMenuid(requestData.getRequest().getMenuid());
            functionResponse.setFunctionlist(functionList);
            web.setResponse(functionResponse);
            return JSONObject.toJSON(web).toString();
        }else{
            // 获取当前菜单所有的功能按钮
            List<Map<String,FunctionResponse.Function>> functionList = functionRepository.functionQuery(map);
            for (int i = 0; i < functionList.size(); i++) {
                HashMap function = (HashMap) functionList.get(i);
                function.put("power","0");
            }
            // 获取有权限的菜单
            List<Map<String, Object>> rolePowerFunction = rolePowerRepository.rolePowerFunctionQuery(map);
            for (int i = 0; i <rolePowerFunction.size() ; i++) {
                for (int ii = 0; ii <functionList.size() ; ii++) {
                    HashMap upf= (HashMap) functionList.get(ii);
                    if (rolePowerFunction.get(i).get("functionid").equals(upf.get("functionid"))){
                        upf.put("power","1");
                    }
                }
            }
            //创建接收对象
            WebResponse<FunctionResponse> web = new WebResponse<>();
            FunctionResponse functionResponse = new FunctionResponse();
            functionResponse.setMenuid(requestData.getRequest().getMenuid());
            functionResponse.setFunctionlist(functionList);
            web.setResponse(functionResponse);
            return JSONObject.toJSON(web).toString();
        }
    }

    /**
     * 功能添加接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String functionAdd(WebRequest<FunctionRequest> requestData) {
        //对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        map.put("uuid", CommonUtil.getUUid());
        map.put("addTime", DateTimeUtil.getTimeformat());
        map.put("operator", requestData.getUserid());
        functionRepository.functionAdd(map);
        return new SysResponse().toJsonString();
    }

    /**
     * 功能修改接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String functionUpdate(WebRequest<FunctionRequest> requestData) {
        //对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        map.put("updateTime", DateTimeUtil.getTimeformat());
        map.put("operator", requestData.getUserid());
        functionRepository.functionUpdate(map);
        return new SysResponse().toJsonString();
    }

    /**
     * 功能删除接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String functionDelete(WebRequest<FunctionRequest> requestData) {
        //批量删除  通过，分割  循环
        String[] functionlist = requestData.getRequest().getFunctionidlist();
        // 循环删除
        for (int i = 0; i < functionlist.length; i++) {
            functionRepository.functionDelete(functionlist[i]);
        }
        return new SysResponse().toJsonString();
    }


}
