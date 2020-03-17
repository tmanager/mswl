package com.frank.mswl.ac.login;

import com.alibaba.fastjson.JSONObject;
import com.frank.mswl.core.request.WebRequest;
import com.frank.mswl.core.response.SysErrResponse;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.Md5Util;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.SdyfJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 登录Service.
 *
 * @author 张明亮 2019/08/12.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/12 张明亮 创建.
 */

@Slf4j
@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

//    @Autowired
//    private YfRedisService yfRedisService;

    /**
     * 登录验证接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String login(WebRequest<LoginRequest> requestData){
        // 获取报文体
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        map.put("userid",requestData.getUserid());
        // 创建返回对象
        WebResponse<LoginResponse> web=new WebResponse<>();
        // 登录验证
        Map hashMap= loginRepository.validateLogon(map);
        // 判断用户名与密码是否正确
        if (hashMap!=null){
            String repassword = Md5Util.MD5Encode("123456", "utf8");
            LoginResponse loginResponse=new LoginResponse();
            if (repassword.equals(requestData.getRequest ().getPasswd ())){
                loginResponse.setRepassword ( "0" );
            }else {
                loginResponse.setRepassword ( "1" );
            }
            String token = CommonUtil.getUUid().toUpperCase();
            loginResponse.setToken(token);
            loginResponse.setUserid(hashMap.get("uid").toString());
            //loginResponse.setImage(hashMap.get("image").toString());
            loginResponse.setUsername(hashMap.get("username").toString());
            loginResponse.setOrganid(hashMap.get("organid").toString());
            loginResponse.setOrganname(hashMap.get("organname").toString());
            web.setResponse(loginResponse);
//            yfRedisService.setStringValue(requestData.getHead().getUserid(), token);
//            yfRedisService.expire(requestData.getHead().getUserid(), 1800);
            log.info("用户验证通过："+JSONObject.toJSON(web).toString());
            return JSONObject.toJSON(web).toString();
        }else {
            return new SysErrResponse("用户名或密码错误，请重新输入！").toJsonString();
        }
    }

    /**
     * 验证用户登录是否过期.
     *
     * @return null
     */
    public String overdue(String userid){
//        String user= yfRedisService.getStringValue(userid);
//        if (user==null){
//            return new SysErrResponse("用户登陆超时，请重新登录！").toJsonString();
//        }else{
//            return new SysResponse().toJsonString();
//        }
        return new SysResponse().toJsonString();
    }
    /**
     * 管理系统接口实现.
     *
     * @return null
     */
    public String logout(WebRequest<LoginRequest> requestData){
        //yfRedisService.removeKey(requestData.getHead().getUserid());
        return new SysResponse().toJsonString();
    }
    /**
     * 管理系统接口实现.
     *
     * @return null
     */
    public String tokenquery(WebRequest<LoginRequest> requestData){
        WebResponse<LoginResponse> web=new WebResponse<>();
        LoginResponse loginResponse=new LoginResponse();
        String token = CommonUtil.getUUid().toUpperCase();
        loginResponse.setToken(token);
        web.setResponse(loginResponse);
        return JSONObject.toJSON(web).toString();
    }
}
