package com.frank.mswl.ac.login;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端用户登录Dao.
 *
 * @author 张明亮 2019/08/12.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/12 张明亮 创建.
 */
@Mapper
@Repository
public interface LoginRepository {

    /**
     * 根据用户登录名取得用户信息.
     *
     * @param loginName 登录名.
     *
     * @return 用户信息.
     */
    Map<String, Object> getUserInfo(String loginName);
    /**
     * 登录验证.
     *
     * @param map 登录名与密码.
     *
     * @return 用户信息.
     */
    Map<String, Object> validateLogon(HashMap map);
}
