package com.frank.mswl.mini.coupon;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 上程序端优惠券Repository.
 *
 * @author 张孝党 2020/01/06.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/06. 张孝党 创建.
 */
@Mapper
@Repository
public interface CouponRepository {

    /**
     * 根据openid查询可用优惠券.
     */
    List<Map<String, String>> getCouponList(Map<String, Object> param);

    /**
     * 根据openid查询可用优惠券数量.
     */
    int getCouponListCnt(Map<String, Object> param);

    /**
     * 判断转赠人的手机号是否存在.
     */
    Map<String, String> wxUserPhoneExists(Map<String, String> param);

    /**
     * 获取优惠券现有的状态.
     */
    Map<String, String> getCouponInfo(Map<String, String> param);

    /**
     * 新增一条转增记录.
     */
    int insNewCouponHis(Map<String, String> param);

    /**
     * 更新原有记录.
     */
    int updCouponHis(Map<String, String> param);
}
