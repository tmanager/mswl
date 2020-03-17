package com.frank.mswl.mini.index;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 小程序首页Repository.
 *
 * @author 张孝党 2019/12/25.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/25. 张孝党 创建.
 */
@Mapper
@Repository
public interface IndexRepository {

    /**
     * 查询广告数据.
     */
    List<Map<String, String>> getAdvertList();

    /**
     * 查询特色服务数据.
     */
    List<Map<String, String>> getServiceList();

    /**
     * 查询新人专区数据.
     */
    List<Map<String, String>> getNewbornList();

    /**
     * 查询海外招募数据.
     */
    List<Map<String, String>> getAbroadList();

    /**
     * 查询推荐阅读数据.
     */
    List<Map<String ,String>> getArticleList();

    /**
     * 查询推荐阅读条数.
     */
    int getArticlCnt();
}
