package com.frank.mswl.web.advert;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 轮播广告管理Repository.
 *
 * @author 张孝党 2019/12/21.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/21 张孝党 创建.
 */
@Mapper
@Repository
public interface AdvertRepository {

    /**
     * 查询总条数.
     */
    int getCnt(Map<String, Object> param);

    /**
     * 查询明细.
     */
    List<Map<String, String>> getAdvertList(Map<String, Object> param);

    /**
     * 新增广告.
     */
    int addAdvert(Map<String, String> param);

    /**
     * 删除广告.
     */
    int deleteAdvert(Map<String,String> param);

    /**
     * 更新广告
     */
    int updAdvert(Map<String, String> param);
}
