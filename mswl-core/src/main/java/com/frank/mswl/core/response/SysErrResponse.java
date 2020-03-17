package com.frank.mswl.core.response;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 异常返回信息.
 *
 * @author 张孝党 2019/08/26.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/08/26 张孝党 创建.
 */
public class SysErrResponse extends SysResponse {

    /**
     * 构造方法,默认为异常.
     */
    public SysErrResponse(String errMsg) {
        super("9999", errMsg);
    }

    /**
     * 构造方法,默认为异常.
     */
    public SysErrResponse(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
