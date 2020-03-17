package com.frank.mswl.web.suggest;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 投诉建议Repository.
 *
 * @author 张孝党 2020/01/21.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/21 张孝党 创建.
 */
@Mapper
@Repository
public interface SuggestRepository {

    /**
     * 查询总条数.
     */
    int getCnt(Map<String, Object> param);

    /**
     * 查询明细.
     */
    List<Map<String, String>> getSuggestList(Map<String, Object> param);

    /**
     * 更新投诉建议.
     */
    int updSuggest(Map<String, String> param);
}
