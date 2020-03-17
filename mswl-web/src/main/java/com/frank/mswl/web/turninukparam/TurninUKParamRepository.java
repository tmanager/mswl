package com.frank.mswl.web.turninukparam;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * UK版turnitin参数Repository.
 *
 * @author 张孝党 2019/12/09.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/09 张孝党 创建.
 */
@Mapper
@Repository
public interface TurninUKParamRepository {

    /**
     * 获取UK版参数信息.
     */
    List<Map<String, String>> getTurnitParam();

    /**
     * 更新参数表信息.
     */
    int updTurnitParam(Map<String, String> param);
}
