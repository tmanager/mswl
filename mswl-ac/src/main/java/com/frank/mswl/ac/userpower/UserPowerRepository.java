package com.frank.mswl.ac.userpower;

import com.frank.mswl.ac.function.FunctionResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端用户权限信息Dao.
 *
 * @author 张明亮 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/08 张明亮 创建.
 */
@Mapper
@Repository
public interface UserPowerRepository {

    /**
     * 查询用户权限信息.
     *
     * @param map 用户权限信息.
     *
     */
   List<Map<String,Object>> userPowerQuery(HashMap map);

    /**
     * 查询用户菜单功能权限信息.
     *
     * @param map 用户菜单功能权限信息.
     *
     */
    List<Map<String,Object>> userPowerFunctionQuery(HashMap map);

    /**
     * 添加用户权限信息.
     *
     * @param map 用户权限信息.
     *
     */
    void userPowerAdd(HashMap map);

    /**
     * 删除用户权限信息.
     *
     * @param userid 用户信息列表.
     *
     */
    void userPowerDelete(String userid);

    /**
     * 删除用户权限功能按钮信息.
     *
     * @param userid 用户id.
     *
     */
    void userPowerFunctionDelete(@Param("menuid") String menuid, @Param("userid") String userid);

    /**
     * 添加用户权限功能按钮信息.
     *
     * @param map 用户权限功能按钮信息.
     *
     */
    void userfunctionAdd(HashMap map);
    /**
     * 用户按钮功能信息列表.
     *
     *  @return 菜单列表.
     */
    List<Map<String, FunctionResponse.Function>> userfunctionQuerys(HashMap map);
}
