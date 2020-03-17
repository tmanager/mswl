package com.frank.mswl.mini.coupon;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 上程序端优惠券返回报文.
 *
 * @author 张孝党 2020/01/06.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/06. 张孝党 创建.
 */
@Getter
@Setter
public class CouponResponse extends BaseResponse {

    // 优惠券列表
    private List<Map<String, String>> couponlist;
}
