package com.frank.mswl.mini.weixin;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class WeixinPayRequest extends BaseRequest {

    // OpenID
    @Value("${openid}")
    private String openId = "";

    // 支付金额
    @Value("${amount}")
    private String amount = "";

    // 订单ID列表
    @Value("${orderidlist}")
    private String[] orderIdList;

    // 优惠券ID
    @Value("${coupid}")
    private String coupId = "";
}
