package com.frank.mswl.web.type;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 二手物品，家政服务，房屋出租分类管理Repository.
 *
 * @author 张孝党 2020/03/19.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/03/19 张孝党 创建.
 */
@Mapper
@Repository
public interface TypeRepository {

    /**
     * 查询总条数.
     */
    int getCnt(Map<String, Object> param);

    /**
     * 查询明细.
     */
    List<Map<String, String>> getTypeList(Map<String, Object> param);

    /**
     * 新增二手物品信息.
     */
    int addType(Map<String, String> param);

    /**
     * 删除二手物品信息.
     */
    int deleteType(Map<String, String> param);

    /**
     * 更新二手物品信息
     */
    int updType(Map<String, String> param);
}
