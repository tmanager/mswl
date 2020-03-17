package com.frank.mswl.core.request;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 请求报文基类.
 *
 * @author 张孝党 2019/08/27.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/27 张孝党 创建.
 */
public abstract class SysRequest<T> {

    // token
    private String token = "";

    // 时间戳
    private String timestamp = "";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // 报文体
    private T request;

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
}
