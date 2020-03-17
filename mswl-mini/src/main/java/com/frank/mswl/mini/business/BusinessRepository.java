package com.frank.mswl.mini.business;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 上传文件及价格计算Repository.
 *
 * @author 张孝党 2020/01/03.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/03. 张孝党 创建.
 */
@Mapper
@Repository
public interface BusinessRepository {

    /**
     * 新增订单信息.
     */
    int insOrderInfo(Map<String, String> param);

    /**
     * 根据订单号查询订单信息.
     */
    Map<String, String> getOrderInfoByOrderId(Map<String, String> param);

    /**
     * 查询价格信息.
     */
    Map<String, String> getFeeInfo();

    /**
     * 根据订单号删除订单信息.
     */
    int deleteOrderInfo(Map<String, String> param);

    /**
     * 根据订单号更新订单字数信息.
     */
    int updOrderWordInfo(Map<String, String> param);
}
