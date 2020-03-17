package com.frank.mswl.web.order;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 订单查询Controller.
 *
 * @author 张孝党 2020/01/14.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/14 张孝党 创建.
 */
@Mapper
@Repository
public interface OrderRepository {

    /**
     * 查询订单列表.
     */
    List<Map<String, String>> getOrderList(Map<String, Object> param);

    /**
     * 查询订单数量.
     */
    int getOrderCnt(Map<String, Object> param);

    /**
     * 更新订单报告及状态.
     */
    int updOrderReport(Map<String, String> param);
}
