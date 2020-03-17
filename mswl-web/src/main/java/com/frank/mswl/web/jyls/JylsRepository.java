package com.frank.mswl.web.jyls;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 交易流水查询Repository.
 *
 * @author 张孝党 2020/01/22.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/22 张孝党 创建.
 */
@Mapper
@Repository
public interface JylsRepository {

    /**
     * 查询交易流水列表.
     */
    List<Map<String, String>> getDataList(Map<String, Object> param);

    /**
     * 查询交易流水数量.
     */
    int getDataCnt(Map<String, Object> param);
}
