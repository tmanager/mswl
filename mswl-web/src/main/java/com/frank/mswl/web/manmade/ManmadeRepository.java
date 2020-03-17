package com.frank.mswl.web.manmade;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 人工服务管理Repository.
 *
 * @author 张孝党 2019/12/30.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/30 张孝党 创建.
 */
@Mapper
@Repository
public interface ManmadeRepository {

    /**
     * 查询总条数.
     */
    int getCnt(Map<String, Object> param);

    /**
     * 查询明细.
     */
    List<Map<String, String>> getManmadeList(Map<String, Object> param);

    /**
     * 更新服务
     */
    int updManmade(Map<String, String> param);

    /**
     * 获取服务内容.
     */
    Map<String, Object> getManmadeDetail(Map<String, String> param);
}
