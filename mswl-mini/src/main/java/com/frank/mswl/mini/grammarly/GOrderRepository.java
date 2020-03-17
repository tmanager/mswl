package com.frank.mswl.mini.grammarly;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 上程序语法检测列表Repository.
 *
 * @author 张孝党 2020/01/14.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/14. 张孝党 创建.
 */
@Mapper
@Repository
public interface GOrderRepository {

    /**
     * 查重列表信息.
     */
    List<Map<String, String>> getGrammarlyList(Map<String, Object> param);

    /**
     * 查询价格信息.
     */
    Map<String, String> getFeeInfo();

    /**
     * 删除订单.
     */
    int delGrammarlyOrder(Map<String, String> param);
}
