package com.frank.mswl.ac.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端用户信息Dao.
 *
 * @author 张明亮 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/08 张明亮 创建.
 */
@Mapper
@Repository
public interface UserRepository {

    /**
     * 添加用户信息.
     *
     * @param map 用户信息.
     *
     */
    void userAdd(HashMap map);

    /**
     * 添加用户信息 验证帐号唯一性.
     *
     * @param userid 登录用户名.
     *
     */
    int userOnly(String userid);

    /**
     * 添加用户和机构关联信息.
     *
     * @param map 用户和机构关联信息.
     *
     */
    void userOrganAdd(HashMap map);

    /**
     * 添加用户角色信息.
     *
     * @param map 用户角色关联信息.
     */
    void userRoleAdd(HashMap map);

    /**
     * 删除用户信息.
     *
     * @param userid 用户id.
     *
     */
    void userDelete(String userid);

    /**
     * 删除用户和机构关联信息.
     *
     * @param userid 用户id.
     *
     */
    void userOrganDelete(String userid);

    /**
     * 删除用户和角色关联信息.
     *
     * @param userid 用户id.
     *
     */
    void userRoleDelete(String userid);

    /**
     * 删除用户和功能关联信息.
     *
     * @param userid 用户id.
     *
     */
    void userFunctionDelete(String userid);

    /**
     * 删除用户和功能关联信息.
     *
     * @param userid 用户id.
     *
     */
    void userFunctionDeletes(@Param("userid") String userid, @Param("powerid") String powerid);

    /**
     * 修改用户信息.
     *
     * @param map 用户信息列表.
     *
     */
    void userUpdate(HashMap map);

    /**
     * 修改用户和机构关联信息.
     *
     * @param map 用户和机构关联信息.
     *
     */
    void userOrganUpdate(HashMap map);

    /**
     * 查询用户角色信息列表.
     *
     * @return 用户列表.
     */
    List<UserResponse.UserListResponse> userQueryList(HashMap map);

    /**
     * 查询用户数据总条数.
     *
     * @return 用户列表总条数.
     */
    int number(HashMap map);

    /**
     * 根据用户登录帐号查询用户个人信息.
     *
     *
     * @return 用户信息.
     * @param userid
     */
    Map userQuery(String userid);

    /**
     * 密码重置
     *
     * @param map 用户帐号.
     *
     */
    void passWordRest(HashMap map);


    /**
     * 根据机构名称查询机构id
     *
     * @param organname 机构名称.
     *
     */
    String organId(String organname);

    /**
     * 根据角色名称查询角色id
     *
     * @param rolename 角色名称.
     *
     */
    String roleId(String rolename);




}
