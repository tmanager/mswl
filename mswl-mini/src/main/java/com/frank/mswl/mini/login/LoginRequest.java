package com.frank.mswl.mini.login;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/**
 * 小程序登录/注册请求报文.
 *
 * @author 张孝党 2019/12/27.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/27. 张孝党 创建.
 */
@Getter
@Setter
public class LoginRequest extends BaseRequest {

    // 登录时获取的 code
    private String code = "";

    // openid
    @Value("${openid}")
    private String openId = "";

    // 用户昵称
    @Value("nickName")
    private String nickName = "";

    // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String gender = "";

    // 语言
    private String language = "";

    // 城市
    private String city = "";

    // 省
    private String province = "";

    // 国家
    private String country = "";

    // 头像URL
    @Value("${avatarurl}")
    private String avatarUrl = "";

    // 用户绑定的手机号（国外手机号会有区号）
    @Value("${phonenumber}")
    private String phoneNumber = "";

    // 没有区号的手机号
    @Value("${purephonenumber}")
    private String purePhoneNumber = "";

    // 区号
    @Value("${countrycode}")
    private String countryCode = "";

    // 是否注册,0:未注册,1:已注册
    @Value("${register}")
    private String register = "";

    // 会话密钥
    @Value("${sessionkey}")
    private String sessionKey = "";

    // 包括敏感数据在内的完整用户信息的加密数据
    @Value("${encrypteddata}")
    private String encryptedData = "";

    // 加密算法的初始向量
    private String iv = "";

    // 所在留学的国家
    @Value("${learncountry}")
    private String learnCountry = "";

    // 专业
    private String major = "";

    // email
    private String email = "";
}
