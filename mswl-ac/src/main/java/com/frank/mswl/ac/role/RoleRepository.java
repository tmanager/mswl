package com.frank.mswl.ac.role;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端角色信息Dao.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */
@Mapper
@Repository
public interface RoleRepository {

    /**
     * 查询角色信息列表.
     *
     *  @return 角色列表.
     */
    List<RoleResponse.RoleListResponse> roleQueryList(HashMap map);

    /**
     * 添加角色信息 验证code唯一性.
     *
     * @param rolecode 登录用户名.
     *
     */
    int roleOnly(String rolecode);

    /**
     * 角色信息添加.
     *
     * @param map 角色添加信息.
     */
    void roleAdd(HashMap map);

    /**
     * 角色信息修改.
     *
     * @param map 角色修改信息.
     */
    void roleUpdate(HashMap map);

    /**
     * 删除用户信息.
     *
     * @param roleid 角色id.
     *
     */
    void roleDelete(String roleid);

    /**
     * 查询角色是否被使用.
     *
     * @param roleid 角色id.
     */
    int roleQueryById(String roleid);

    /**
     * 查询角色数据总条数.
     *
     * @return 角色列表总条数.
     */
    int number(HashMap map);



}
