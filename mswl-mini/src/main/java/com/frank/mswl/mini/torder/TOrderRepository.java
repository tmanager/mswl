package com.frank.mswl.mini.torder;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TOrderRepository {

    /**
     * 查重列表信息.
     */
    List<Map<String, String>> getTurnitinList(Map<String, Object> param);

    /**
     * 查询价格信息.
     */
    Map<String, String> getFeeInfo();

    /**
     * 删除订单.
     */
    int delTurnitinOrder(Map<String, String> param);
}
