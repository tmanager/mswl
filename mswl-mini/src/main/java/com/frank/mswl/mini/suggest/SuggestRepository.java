package com.frank.mswl.mini.suggest;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface SuggestRepository {

    /**
     * 按IP查询提交次数.
     */
    int querySubmitCnt(Map<String, String> param);

    /**
     * 新增建议.
     */
    int insSuggest(Map<String, String> param);
}
