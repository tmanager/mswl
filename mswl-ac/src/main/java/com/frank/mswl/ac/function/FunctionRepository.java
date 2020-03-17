package com.frank.mswl.ac.function;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端功能信息Dao.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */
@Mapper
@Repository
public interface FunctionRepository {

    /**
     * 查询菜单信息列表.
     *
     *  @return 菜单列表.
     */
    List<Map<String, FunctionResponse.Function>> functionQuery(HashMap map);

    /**
     * 功能信息添加.
     *
     * @param map 功能添加信息.
     */
    void functionAdd(HashMap map);

    /**
     * 功能信息修改.
     *
     * @param map 功能修改信息.
     */
    void functionUpdate(HashMap map);

    /**
     * 删除功能信息.
     *
     * @param functionid 菜单id.
     *
     */
    void functionDelete(String functionid);

    /**
     * 查询功能权限信息.用户添加或修改角色时使用.
     *
     * @param menuid 菜单id.
     *
     */
    List<Map> menuFunctionQuery(@Param("menuid") String menuid, @Param("roleid") String roleid);

}
