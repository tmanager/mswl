package com.frank.mswl.ac.menu;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端菜单信息Dao.
 *
 * @author 张明亮 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/08 张明亮 创建.
 */
@Mapper
@Repository
public interface MenuRepository {

    /**
     * 查询菜单信息列表.
     *
     *  @return 菜单列表.
     */
    List<MenuResponse.MenuTree> menuQueryList(HashMap map);

    /**
     * 查询菜单信息列表.递归
     *
     *  @return 菜单列表.
     */
    List<Map<String, Object>> menuQueryLists(HashMap map);

    /**
     * 查询用户功能权限菜单列表.
     *
     *  @return 菜单列表.
     */
    List<MenuResponse.MenuTree> userfunctionQuery(String userid);

    /**
     * 查询用户功能权限菜单列表.递归用
     *
     *  @return 菜单列表.
     */
    List<Map<String, Object>> userfunctionQuerys(String userid);

    /**
     * 查询角色功能权限菜单列表.
     *
     *  @return 菜单列表.
     */
    List<MenuResponse.MenuTree> rolefunctionQuery(String roleid);

    /**
     * 查询角色功能权限菜单列表.递归
     *
     *  @return 菜单列表.
     */
    List<Map<String, Object>> rolefunctionQuerys(String roleid);

    /**
     * 菜单信息添加.
     *
     * @param map 菜单添加信息.
     */
    void menuAdd(HashMap map);

    /**
     * 菜单信息修改.
     *
     * @param map 菜单修改信息.
     */
    void menuUpdate(HashMap map);

    /**
     * 删除菜单信息.
     *
     * @param menuid 菜单id.
     *
     */
    void menuDelete(String menuid);

    /**
     * 查询菜单数据总条数.
     *
     * @return 菜单列表总条数.
     */
    int number();

    /**
     * 查询本菜单下是否存在子菜单.
     *
     * @return 子菜单数量.
     */
    int sonMenu(String menuid);

    /**
     * 查询菜单代码唯一.
     *
     * @return 菜单数量.
     */
    int menuOnly(String menucode);


}
