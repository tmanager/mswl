package com.frank.mswl.ac.regulate;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 参数信息Dao.
 *
 * @author 何楠 2019/08/17.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/17 何楠 创建.
 */
@Mapper
@Repository
public interface RegulateRepository {


    /**
     * 查询参数信息列表.
     *
     * @return 参数列表.
     */
    List<RegulateResponse.RegulateResponseList> regulateQueryList(HashMap hashMap);

    /**
     * 添加参数
     *
     * @param hashMap 参数信息.
     */
    void regulateAdd(HashMap hashMap);

    /**
     * 修改参数
     *
     * @param hashMap 参数信息.
     */
    void regulateUpdate(HashMap hashMap);

    /**
     * 删除参数
     *
     * @param regid 参数id.
     */
    void regulateDelete(String regid);

    /**
     * 查询参数数量.
     *
     * @return 数量.
     */
    int getNumber(HashMap hashMap);

}

