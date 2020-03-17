package com.frank.mswl.web.abroad;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 海外招聘管理Repository.
 *
 * @author 张孝党 2019/12/23.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/23 张孝党 创建.
 */
@Mapper
@Repository
public interface AbroadRepository {

    /**
     * 查询总条数.
     */
    int getCnt(Map<String, Object> param);

    /**
     * 查询明细.
     */
    List<Map<String, String>> getAbroadList(Map<String, Object> param);

    /**
     * 新增招募信息.
     */
    int addAbroad(Map<String, String> param);

    /**
     * 删除招募信息.
     */
    int deleteAbroad(Map<String,String> param);

    /**
     * 更新招募信息
     */
    int updAbroad(Map<String, String> param);

    /**
     * 获取招募内容.
     */
    Map<String, Object> getAbroadDetail(Map<String, String> param);
}
