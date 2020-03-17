package com.frank.mswl.web.coupon;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 优惠券请求报文.
 *
 * @author 张孝党 2020/01/07.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2020/01/07 张孝党 创建.
 */
@Getter
@Setter
public class CouponRequest extends BaseRequest {

    // 优惠券ID
    private String id = "";

    // 优惠券名称
    private String couponname = "";

    // 优惠券描述
    private String couponbak = "";

    // 需要兑换的积分
    private String usemark = "";

    // 满多少元可用
    private String upfee = "";

    // 发行个数
    private String numbers = "";

    // 有效结束日期
    private String enddate = "";

    // 分类
    private String type = "";

    // 地用多少钱
    private String amount = "";

    // 状态
    private String status = "";

    // 删除的优惠券id列表
    private String[] coupidlist;

    // 手机号码
    private String phone = "";
}
