package com.frank.mswl.web.suggest;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 投诉建议请求报文.
 *
 * @author 张孝党 2020/01/21.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2020/01/21 张孝党 创建.
 */
@Getter
@Setter
public class SuggestRequest extends BaseRequest {

    // 手机号
    private String phone = "";

    // 开始时间
    private String starttime = "";

    // 结束时间
    private String endtime = "";

    // 投诉建议ID
    private String id = "";

    // 处理意见
    private String handle = "";

    // 状态
    private String status = "";
}
