package com.frank.mswl.web.coupon;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 优惠券返回报文.
 *
 * @author 张孝党 2020/01/07.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2020/01/07 张孝党 创建.
 */
@Getter
@Setter
public class CouponReponse extends BaseResponse {

    // 数据列表
    private List<Map<String, String>> couplist;

    // 数据详细列表
    private List<Map<String, String>> couphislist;

    // 分类
    private String type = "";

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

    // 抵用多少钱
    private String amount = "";

    // 状态
    private String status = "";

}
