package com.frank.mswl.web.manmade;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 人工服务管理请求报文.
 *
 * @author 张孝党 2019/12/30.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/30 张孝党 创建.
 */
@Getter
@Setter
public class ManmadeRequest extends BaseRequest {

    // id
    private String manmadeid = "";

    // 服务名称
    private String title = "";

    // 服务内容
    private String content = "";
}
