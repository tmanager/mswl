package com.frank.mswl.ac.organ;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端机构信息Dao.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */
@Mapper
@Repository
public interface OrganRepository {

    /**
     * 查询机构信息列表.
     *
     *  @return 机构列表.
     */
    List<OrganResponse.OrganTree> organQueryList(HashMap map);

    /**
     * 查询机构信息列表_递归.
     *
     *  @return 机构列表.
     */
    List<Map<String, Object>> organQueryLists(HashMap map);

    /**
     * 机构信息添加.
     *
     * @param map 机构添加信息.
     */
    void organAdd(HashMap map);

    /**
     * 机构信息修改.
     *
     * @param map 机构修改信息.
     */
    void organUpdate(HashMap map);

    /**
     * 删除机构信息.
     *
     * @param organid 机构id.
     *
     */
    void organDelete(String organid);

    /**
     * 查询机构数据总条数.
     *
     * @return 机构列表总条数.
     */
    int number();

    /**
     * 查询本机构下是否存在子机构.
     *
     * @return 子机构数量.
     */
    int sonOrgan(String organid);

    /**
     * 查询机构名称唯一.
     *
     * @param hashMap 机构信息.
     */
    int organOnly(HashMap hashMap);

    /**
     * 查询机构名称根据id.
     *
     * @param organid 机构id.
     */
    String organNameById(String organid);

}
