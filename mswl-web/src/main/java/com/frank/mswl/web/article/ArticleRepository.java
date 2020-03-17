package com.frank.mswl.web.article;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 推荐阅读Repository.
 *
 * @author 张孝党 2019/12/16.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/16 张孝党 创建.
 */
@Mapper
@Repository
public interface ArticleRepository {

    /**
     * 查询总条数.
     */
    int getCnt(Map<String, Object> param);

    /**
     * 查询明细.
     */
    List<Map<String, String>> getArticleList(Map<String, Object> param);

    /**
     * 新增文章.
     */
    int addArticle(Map<String, String> param);

    /**
     * 获取文章内容.
     */
    Map<String, String> getArtDetail(Map<String, String> param);

    /**
     * 删除文章.
     */
    int deleteArticle(Map<String,String> param);

    /**
     * 更新文章.
     */
    int updArticle(Map<String, String> param);

    /**
     * 更新阅读次数.
     */
    int updReadCount(Map<String, String> param);
}
