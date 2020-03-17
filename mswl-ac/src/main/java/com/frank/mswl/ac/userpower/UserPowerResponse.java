package com.frank.mswl.ac.userpower;

import com.frank.mswl.ac.function.FunctionResponse;

import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端用户权限返回报文.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */
public class UserPowerResponse {

    // 菜单列表
    List<menulist>menulist;

    // 按钮列表
    List<FunctionResponse> usermenulist;

    public List<FunctionResponse> getUsermenulist() {
        return usermenulist;
    }

    public void setUsermenulist(List<FunctionResponse> usermenulist) {
        this.usermenulist = usermenulist;
    }

    public List<UserPowerResponse.menulist> getMenulist() {
        return menulist;
    }

    public void setMenulist(List<UserPowerResponse.menulist> menulist) {
        this.menulist = menulist;
    }

    // 主菜单
    public static class menulist{
        // 菜单id
        private String menuid;

        // 菜单名称
        private String menuname;

        // 菜单路径
        private String url;

        // 菜单类型
        private String menutype;

        // 菜单排序号
        private String sort;

        // 权限 0：无权限，1有权限
        private String power;

        // 子菜单列表（如果没有子菜单，该字段不存在；如果有子菜单，该字段存在）
        List<Submenu>Submenulist;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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

        public List<Submenu> getSubmenulist() {
            return Submenulist;
        }

        public void setSubmenulist(List<Submenu> submenulist) {
            Submenulist = submenulist;
        }

        @Override
        public String toString() {
            return "menulist{" +
                    "menuid='" + menuid + '\'' +
                    ", menuname='" + menuname + '\'' +
                    ", url='" + url + '\'' +
                    ", menutype='" + menutype + '\'' +
                    ", sort='" + sort + '\'' +
                    ", power='" + power + '\'' +
                    ", Submenulist=" + Submenulist +
                    '}';
        }
    }

    // 子菜单
    public static class Submenu{

        // 菜单id
        private String menuid;

        // 菜单名称
        private String menuname;

        // 菜单路径
        private String url;

        // 菜单类型
        private String menutype;

        // 菜单排序号
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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

        @Override
        public String toString() {
            return "Submenu{" +
                    "menuid='" + menuid + '\'' +
                    ", menuname='" + menuname + '\'' +
                    ", url='" + url + '\'' +
                    ", menutype='" + menutype + '\'' +
                    ", sort='" + sort + '\'' +
                    ", power='" + power + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserPowerResponse{" +
                "menulist=" + menulist +
                '}';
    }
}
