package com.frank.mswl.ac.login;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端请求报文.
 *
 * @author 张明亮 2019/08/12.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/12 张明亮 创建.
 */
public class LoginRequest {

    // 密码
    private String passwd;

    // 用户登录名
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }


}
