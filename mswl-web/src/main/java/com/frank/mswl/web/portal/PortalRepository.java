package com.frank.mswl.web.portal;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 首页数据查询Repository.
 *
 * @author 张孝党 2020/01/18.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/18 张孝党 创建.
 */
@Mapper
@Repository
public interface PortalRepository {

    /**
     * 获取当天注册会员数量.
     */
    int getTodayUserCnt(Map<String, String> param);

    /**
     * 获取总会员注册数量.
     */
    int getTotalUserCnt();

    /**
     * 获取完善信息用户数.
     */
    int getPerUserCnt();

    /**
     * 获取订单数.
     */
    int getTotalOrderCnt(Map<String, String> param);

    /**
     * 获取订单金额.
     */
    int getTotalOrderAmount(Map<String, String> param);

    /**
     * 获取今日订单数.
     */
    int getTodayOrderCnt(Map<String, String> param);

    /**
     * 获取今日订单金额.
     */
    int getTodayOrderAmount(Map<String, String> param);

    /**
     * 获取订单趋势数据.
     */
    List<Map<String, String>> getLineData(Map<String, String> param);

    List<Map<String, String>> getStatusData();

    List<Map<String, String>> getCheckTypeData();
}
