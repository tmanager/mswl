package com.frank.mswl.core.response;

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
public class WebResponse<T> {

    // 响应码
    private String retcode = "0000";

    // 响应内容
    private String retmsg = "调用成功";

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    // 报文体
    private T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

}
