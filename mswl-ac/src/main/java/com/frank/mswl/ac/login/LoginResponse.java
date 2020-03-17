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
public class LoginResponse {

    // 用户名
    private String username;

    // 用户头像
    private String image;

    // 登录后生成的token
    private String token;

    private String userid;

    // 机构id
    private String organid;

    // 机构名称
    private String organname;

    // 密码验证
    private String repassword;

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getOrganname() {
        return organname;
    }

    public void setOrganname(String organname) {
        this.organname = organname;
    }
    public String getOrganid() {
        return organid;
    }

    public void setOrganid(String organid) {
        this.organid = organid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
