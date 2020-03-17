package com.frank.mswl.web.tprice;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * turnitin价格设置Repository.
 *
 * @author 张孝党 2019/12/25.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/25 张孝党 创建.
 */
@Mapper
@Repository
public interface TPriceRepository {

    /**
     * 获取参数信息.
     */
    Map<String, String> getTprice();

    /**
     * 更新参数表信息.
     */
    int updTprice(Map<String, String> param);
}
