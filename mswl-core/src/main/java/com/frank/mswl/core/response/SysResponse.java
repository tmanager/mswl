package com.frank.mswl.core.response;

import com.alibaba.fastjson.JSONObject;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 系统正常返回信息.
 *
 * @author 张孝党 2019/08/27.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/08/27 张孝党 创建.
 */

public class SysResponse {

    // 返回码，默认0000
    private String retCode = "0000";

    // 消息
    private String retMsg = "调用成功";

    public SysResponse() {

    }

    public SysResponse(String retMsg) {
        this.retMsg = retMsg;
    }

    public SysResponse(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    /**
     * 转换为String型的json字符串.
     *
     * @return 字符串.
     */
    public String toJsonString() {

        // 返回报文
        JSONObject response = new JSONObject();
        response.put("retcode", this.retCode);
        response.put("retmsg", this.retMsg);

        return response.toJSONString();
    }
}
