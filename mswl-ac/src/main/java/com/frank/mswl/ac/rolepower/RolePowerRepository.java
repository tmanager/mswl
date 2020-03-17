package com.frank.mswl.ac.rolepower;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端角色权限信息Dao.
 *
 * @author 张明亮 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/08 张明亮 创建.
 */
@Mapper
@Repository
public interface RolePowerRepository {

    /**
     * 查询角色权限信息.
     *
     * @param map 角色权限信息.
     */
    List<Map<String, Object>> rolePowerQuery(HashMap map);

    /**
     * 批量查询角色权限信息_用户添加角色时使用.
     *
     * @param rolelist 角色权限信息.s
     */
    List<Map<String, Object>> rolePowerQuerys(@Param("rolelist") String[] rolelist);

    /**
     * 查询角色菜单功能权限信息.
     *
     * @param map 角色菜单功能权限信息.
     */
    List<Map<String, Object>> rolePowerFunctionQuery(HashMap map);

    /**
     * 添加角色权限信息.
     *
     * @param map 角色权限信息.
     */
    void rolePowerAdd(HashMap map);

    /**
     * 删除角色权限信息.
     *
     * @param roleid 角色信息列表.
     */
    void rolePowerDelete(String roleid);

    /**
     * 删除角色权限功能按钮信息.
     *
     * @param roleid 角色id.
     */
    void rolePowerFunctionDelete(@Param("roleid") String roleid, @Param("menuid") String menuid);

    /**
     * 添加角色权限功能按钮信息.
     *
     * @param map 角色权限功能按钮信息.
     */
    void rolefunctionAdd(HashMap map);

    /**
     * 根据角色获取用户id.
     *
     * @param map 角色id.
     */
    List<Map<String, String>> rolegetuser(HashMap map);

    /**
     * 根据获取用户有哪些角色.
     *
     * @param userid 用户id.
     */
    List<Map<String, String>> usergetrolenum(String userid);
}
