package com.frank.mswl.web.jyls;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class JylsRequest extends BaseRequest {

    // 开始日期
    @Value("${startdate}")
    private String startDate = "";

    // 结束日期
    @Value("${enddate}")
    private String endDate = "";

    // 商户订单号
    @Value("${tradeno}")
    private String tradeNo = "";

    // 客户手机号
    @Value("${phone}")
    private String phone = "";
}
