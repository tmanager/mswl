package com.frank.mswl.mini.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.DateTimeUtil;
import com.frank.mswl.mini.mark.MarkRepository;
import com.frank.mswl.mini.weixin.WeixinApi;
import com.frank.mswl.mini.weixin.WeixinDecryptDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 小程序登录/注册Service.
 *
 * @author 张孝党 2019/12/27.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/27. 张孝党 创建.
 * 　　　　　 V1.01 2020/03/02. 张孝党 118行增加判断该用户的openid是否已存在.
 */
@Slf4j
@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    // 积分信息Repository
    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private LoginAsync loginAsync;

    /**
     * 账号校验.
     */
    public String checkService(LoginRequest requestData) {

        String openId = "";
        String sessionKey = "";
        //String unionid = "";
        // 是否已注册
        String isRegister = "0";
        // 积分
        String mark = "0";

        // 获取微信用户openid
        WeixinApi api = new WeixinApi();
        JSONObject result = JSON.parseObject(api.getAuthCode2Session(requestData.getCode()));
        // 成功
        if (!result.containsKey("errcode")) {
            openId = result.getString("openid");
            sessionKey = result.getString("session_key");

            // 判断用户是否注册
            Map<String, Object> param = new HashMap<>();
            param.put("openid", openId);
            Map<String, String> wxUserInfo = this.loginRepository.getWxUserCnt(param);
            if (wxUserInfo == null || wxUserInfo.isEmpty()) {
                // 未注册
                isRegister = "0";
            } else {
                // 已注册
                isRegister = "1";

                // 获取积分信息
                Map<String, String> markMap = this.markRepository.getMarkByOpenId(param);
                log.info("获取到的个人积分信息为：[{}]", JSON.toJSONString(markMap, SerializerFeature.PrettyFormat));
                if (markMap == null || markMap.isEmpty()) {
                    mark = "0";
                } else {
                    mark = String.valueOf(markMap.get("mark"));
                }
            }
        }

        // 返回报文
        WebResponse<LoginResponse> responseData = new WebResponse<>();
        LoginResponse loginResponse = new LoginResponse();
        responseData.setResponse(loginResponse);

        // 返回数据
        loginResponse.setOpenid(openId);
        loginResponse.setSession_key(sessionKey);
        //loginResponse.setUnionid(unionid);
        loginResponse.setRegister(isRegister);
        loginResponse.setMark(mark);

        // 返回
        log.info("返回的数据为：\n{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));
        return JSON.toJSONString(responseData);
    }

    /**
     * 更新用户信息.
     */
    @Transactional(rollbackFor = Exception.class)
    public String updUserInfoService(LoginRequest requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        param.put("openid", requestData.getOpenId());
        param.put("avatarurl", requestData.getAvatarUrl());
        param.put("nick_name", requestData.getNickName());
        param.put("gender", requestData.getGender());
        param.put("city", requestData.getCity());
        param.put("province", requestData.getProvince());
        param.put("country", requestData.getCountry());

        // ADD BY zhangxd ON 20200302 START
        // 先判断openid是否存在
        log.info("先行判断一下用户是否已注册...............");
        Map<String, Object> param2 = new HashMap<>();
        param2.put("openid", requestData.getOpenId());
        Map<String, String> wxUserInfo = this.loginRepository.getWxUserCnt(param2);
        if (wxUserInfo == null || wxUserInfo.isEmpty()) {
            // 未注册
            requestData.setRegister("0");
            log.info("该用户openid[{}]未注册.", requestData.getOpenId());
        } else {
            // 已注册
            requestData.setRegister("1");
            log.info("该用户openid[{}]已注册.", requestData.getOpenId());
        }
        // ADD BY zhangxd ON 20200302 END

        // 用户未注册时
        if (requestData.getRegister().equals("0")) {
            // 手机号数据解密
            String phoneInfo = WeixinDecryptDataUtil.decryptData(requestData.getEncryptedData(), requestData.getSessionKey(), requestData.getIv());
            log.info("手机号数据解密为：{}", phoneInfo);
            JSONObject jsonPhoneInfo = JSONObject.parseObject(phoneInfo);

            param.put("phonenumber", jsonPhoneInfo.getString("phoneNumber"));
            param.put("pure_phone_number", jsonPhoneInfo.getString("purePhoneNumber"));
            param.put("country_code", jsonPhoneInfo.getString("countryCode"));
            param.put("updtime", DateTimeUtil.getTimeformat());

            // 新增
            param.put("id", CommonUtil.getUUid());
            int cnt = this.loginRepository.insWxUserInfo(param);
            log.info("新增微信用户条数：{}", cnt);

            // 赠送2个语法免费检测优惠券
            this.loginAsync.addGrammarlyCoupon(requestData.getOpenId());
        } else {
            // 已注册时更新
            int cnt = this.loginRepository.updWxUserInfo(param);
            log.info("更新用户信息条数：{}", cnt);
        }

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 查询个人积分.
     */
    public String queryUserMarkService(String openId) {

        // 积分
        String mark = "0";

        if (openId.isEmpty()) {
            mark = "0";
        } else {
            // 判断用户是否注册
            Map<String, Object> param = new HashMap<>();
            param.put("openid", openId);
            Map<String, String> wxUserInfo = this.loginRepository.getWxUserCnt(param);
            if (wxUserInfo == null || wxUserInfo.isEmpty()) {
                // 未注册
                mark = "0";
            } else {
                // 获取积分信息
                Map<String, String> markMap = this.markRepository.getMarkByOpenId(param);
                log.info("获取到的个人积分信息为：[{}]", JSON.toJSONString(markMap, SerializerFeature.PrettyFormat));
                if (markMap == null || markMap.isEmpty()) {
                    mark = "0";
                } else {
                    mark = String.valueOf(markMap.get("mark"));
                }
            }
        }

        // 返回报文
        WebResponse<LoginResponse> responseData = new WebResponse<>();
        LoginResponse loginResponse = new LoginResponse();
        responseData.setResponse(loginResponse);

        // 返回数据
        loginResponse.setOpenid(openId);
        loginResponse.setMark(mark);

        // 返回
        log.info("返回的数据为：\n{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));
        return JSON.toJSONString(responseData);
    }

    /**
     * 查询个人其它信息.
     */
    public String queryPerInfoService(String openId) {

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("openid", openId);
        Map<String, String> perInfo = this.loginRepository.getWxUserCnt(param);

        // 返回报文
        WebResponse<LoginResponse> responseData = new WebResponse<>();
        LoginResponse loginResponse = new LoginResponse();
        responseData.setResponse(loginResponse);

        // 返回数据
        loginResponse.setLearncountry(perInfo.get("learn_country"));
        loginResponse.setMajor(perInfo.get("major"));
        loginResponse.setEmail(perInfo.get("email"));

        // 返回
        log.info("返回的数据为：\n{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));
        return JSON.toJSONString(responseData);
    }

    /**
     * 保存个人信息.
     */
    public String savePerInfoService(LoginRequest requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        param.put("openid", requestData.getOpenId());
        param.put("major", requestData.getMajor());
        param.put("learncountry", requestData.getLearnCountry());
        param.put("email", requestData.getEmail());

        // 查询是否完善
        Map<String, String> perFlag = this.loginRepository.getWxUserPerFlagByOpenId(param);
        log.info("openid[{}]查询出的个人信息是否完善结果为：\n{}", requestData.getOpenId(),
                JSON.toJSONString(perFlag, SerializerFeature.PrettyFormat));

        // 更新
        this.loginRepository.updWxUserPerInfo(param);

        if (!perFlag.get("perflag").equals("1")) {
            log.info("赠送[{}]个免费查重检测优惠券.", requestData.getOpenId());
            // 赠送1个免费查重检测优惠券
            this.loginAsync.addTurnitinCoupon(requestData.getOpenId());
        } else {
            log.info("[{}]个人信息已完善!", requestData.getOpenId());
        }

        // 返回
        return new SysResponse("保存成功").toJsonString();
    }
}
