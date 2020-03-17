package com.frank.mswl.web.order;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class OrderRequest extends BaseRequest {

    // 开始日期
    @Value("${startdate}")
    private String startDate = "";

    // 结束日期
    @Value("${enddate}")
    private String endDate = "";

    // 分类
    @Value("${checktype}")
    private String checkType = "";

    // 订单状态
    @Value("${status}")
    private String status = "";

    // 订单ID
    @Value("${orderid}")
    private String orderId = "";

    // 客户手机号
    @Value("${phone}")
    private String phone = "";
}
