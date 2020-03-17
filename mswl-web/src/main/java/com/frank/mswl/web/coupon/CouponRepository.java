package com.frank.mswl.web.coupon;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 优惠券Repository.
 *
 * @author 张孝党 2020/01/07.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/07 张孝党 创建.
 */
@Mapper
@Repository
public interface CouponRepository {

    /**
     * 查询总条数.
     */
    int getCnt(Map<String, Object> param);

    /**
     * 查询明细.
     */
    List<Map<String, String>> getCouponList(Map<String, Object> param);

    /**
     * 新增优惠券.
     */
    int addCoupon(Map<String, String> param);

    /**
     * 新增优惠券赠送情报.
     */
    int addCouponHis(Map<String, String> param);

    /**
     * 获取优惠券内容.
     */
    List<Map<String, String>> getCouponDetail(Map<String, Object> param);

    /**
     * 查询总条数.
     */
    int getHisCnt(Map<String, Object> param);

    /**
     * 删除优惠券.
     */
    int deleteCoupon(Map<String, String> param);

    /**
     * 更新优惠券.
     */
    int updCoupon(Map<String, String> param);

    /**
     * 获取微信用户openid.
     */
    String getOpenid(String phone);

    /**
     * 校验是否还能赠送
     */
    int chkUseNumbers(String couponid);

    /**
     * 更新使用个数.
     */
    int updUseNumbers(String couponid);
}
