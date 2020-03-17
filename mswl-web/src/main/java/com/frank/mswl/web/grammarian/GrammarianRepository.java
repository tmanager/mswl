package com.frank.mswl.web.grammarian;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * grammarly参数Repository.
 *
 * @author 张孝党 2019/12/12.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/12 张孝党 创建.
 */
@Mapper
@Repository
public interface GrammarianRepository {

    /**
     * 获取grammarly参数信息.
     */
    List<Map<String, String>> getGrammarlyParam();

    /**
     * 更新参数表信息.
     */
    int updGrammarlyParam(Map<String, String> param);
}
