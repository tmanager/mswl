package com.frank.mswl.mini.mark;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 上程序端积分信息Repository.
 *
 * @author 张孝党 2020/01/08.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/08. 张孝党 创建.
 */
@Mapper
@Repository
public interface MarkRepository {

    /**
     * 根据openid获取积分.
     */
    Map<String, String> getMarkByOpenId(Map<String, Object> param);

    /**
     * 根据openid获取积分列表.
     */
    List<Map<String, String>> getMarkListByOpenId(Map<String, Object> param);

    /**
     * 判断转赠人的手机号是否存在.
     */
    Map<String, String> wxUserPhoneExists(Map<String, String> param);

    /**
     * 新增积分记录.
     */
    int insNewMarkHis(Map<String, String> param);

    /**
     * 查询可用优惠券.
     */
    List<Map<String, String>> getCanUseConponList(Map<String, String> param);

    /**
     * 查询优惠券详情.
     */
    Map<String, String> getCouponInfo(Map<String, String> param);

    /**
     * 更新优惠券信息:状态和数量.
     */
    int updCouponInfo(Map<String, String> param);

    /**
     * 新增个人优惠券记录.
     */
    int insNewCouponHis(Map<String, String> param);
}
