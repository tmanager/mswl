package com.frank.mswl.ac.rolepower;

import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端角色权限返回报文.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */
public class RolePowerResponse {

    private List<RoleMenulist>menulist;

    public List<RoleMenulist> getMenulist() {
        return menulist;
    }

    public void setMenulist(List<RoleMenulist> menulist) {
        this.menulist = menulist;
    }

    public static class RoleMenulist{

        // 菜单ID
        private String menuid;

        // 菜单名称
        private String menuname;

        // 菜单类型
        private String menutype;

        // 排序号
        private String sort;

        // 权限 0：无权限，1有权限
        private String power;

        // 子菜单列表（如果没有子菜单，该字段不存在；如果有子菜单，该字段存在）
        private List<RoleMenulistS> menulist;

        public String getMenuid() {
            return menuid;
        }

        public void setMenuid(String menuid) {
            this.menuid = menuid;
        }

        public String getMenuname() {
            return menuname;
        }

        public void setMenuname(String menuname) {
            this.menuname = menuname;
        }

        public String getMenutype() {
            return menutype;
        }

        public void setMenutype(String menutype) {
            this.menutype = menutype;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public List<RoleMenulistS> getMenulist() {
            return menulist;
        }

        public void setMenulist(List<RoleMenulistS> menulist) {
            this.menulist = menulist;
        }
    }
    public static class RoleMenulistS{

        // 菜单ID
        private String menuid;

        // 菜单名称
        private String menuname;

        // 菜单类型
        private String menutype;

        // 排序号
        private String sort;

        // 权限 0：无权限，1有权限
        private String power;

        public String getMenuid() {
            return menuid;
        }

        public void setMenuid(String menuid) {
            this.menuid = menuid;
        }

        public String getMenuname() {
            return menuname;
        }

        public void setMenuname(String menuname) {
            this.menuname = menuname;
        }

        public String getMenutype() {
            return menutype;
        }

        public void setMenutype(String menutype) {
            this.menutype = menutype;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }
    }



}
