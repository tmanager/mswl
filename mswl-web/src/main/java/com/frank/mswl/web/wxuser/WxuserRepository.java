package com.frank.mswl.web.wxuser;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 微信用户管理Repository.
 *
 * @author 张孝党 2020/01/21.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/21 张孝党 创建.
 */
@Mapper
@Repository
public interface WxuserRepository {

    /**
     * 查询总条数.
     */
    int getCnt(Map<String, Object> param);

    /**
     * 查询明细.
     */
    List<Map<String, String>> getWxuserList(Map<String, Object> param);
}
