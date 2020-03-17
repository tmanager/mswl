package com.frank.mswl.core.request;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端请求报文.
 *
 * @author 张孝党 2019/08/27.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/27 张孝党 创建.
 */
public class WebRequest<T> extends SysRequest<T> {

    // 用户ID
    private String userid = "";

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
