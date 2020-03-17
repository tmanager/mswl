package com.frank.mswl.mini.login;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * 小程序登录/注册返回报文.
 *
 * @author 张孝党 2019/12/27.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/27. 张孝党 创建.
 */
@Getter
@Setter
public class LoginResponse extends BaseResponse {

    // openid
    private String openid = "";

    // 会话密钥
    private String session_key = "";

    // 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回
    private String unionid = "";

    // 是否已注册（注册1，未注册0）
    private String register = "0";

    // 积分
    private String mark = "0";

    // 所在留学的国家
    private String learncountry = "";

    // 专业
    private String major = "";

    // email
    private String email = "";
}
