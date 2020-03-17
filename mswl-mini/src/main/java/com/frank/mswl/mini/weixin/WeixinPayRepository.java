package com.frank.mswl.mini.weixin;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 微信支付Repository.
 *
 * @author 张孝党 2020/01/04.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/04. 张孝党 创建.
 */
@Mapper
@Repository
public interface WeixinPayRepository {

    /**
     * 更新订单的商户订单号.
     */
    int updOrderByOrderId(Map<String, String> param);

    /**
     * 新增支付流水.
     */
    int insJyLs(Map<String, String> param);

    /**
     * 更新订单的商户订单号.
     */
    int updOrderByTradeNo(Map<String, String> param);

    /**
     * 根据商户订单号获取优惠券ID.
     */
    Map<String, String> getCouponByTradeNo(Map<String, String> param);

    /**
     * 根据优惠券ID更新状态.
     */
    int updCouponStatus(Map<String, String> param);

    /**
     * 增加个人积分.
     */
    int insMarkHis(Map<String, String> param);

    /**
     * 查询个人消费记录.
     */
    List<Map<String, String>> queryConsumeListByOpenId(Map<String, Object> param);

    /**
     * 根据支付商户订单号查询订单信息.
     */
    List<Map<String, String>> getOrderInfoByTradeno(Map<String, String> param);

    /**
     * 根据订单号查询订单信息.
     */
    Map<String, String> getOrderInfoByOrderId(Map<String, String> param);

    /**
     * 根据支付商户订单号更新论文ID.
     */
    int updThesisIdByOrderId(Map<String, String> param);

    /**
     * 增加失败订单.
     */
    int insFailureOrder(Map<String, String> param);

    /**
     * 删除失败订单.
     */
    int delFailureOrder(Map<String, String> param);
}
