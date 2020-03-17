package com.frank.mswl.web.newborn;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 新人专区管理Repository.
 *
 * @author 张孝党 2019/12/24.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/24 张孝党 创建.
 */
@Mapper
@Repository
public interface NewBornRepository {

    /**
     * 查询总条数.
     */
    int getCnt(Map<String, Object> param);

    /**
     * 查询明细.
     */
    List<Map<String, String>> getDataList(Map<String, Object> param);

    /**
     * 新增新人专区信息.
     */
    int addNewBorn(Map<String, String> param);

    /**
     * 删除新人专区信息.
     */
    int deleteNewBorn(Map<String,String> param);

    /**
     * 更新新人专区信息
     */
    int updNewBorn(Map<String, String> param);

    /**
     * 获取新人专区内容.
     */
    Map<String, Object> getNewBornDetail(Map<String, String> param);
}
