package com.frank.mswl.ac.menu;

import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端菜单信息返回报文.
 *
 * @author 张明亮 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/08 张明亮 创建.
 */
public class MenuResponse {

    // 请求次数
    private String draw;

    // 总条数（不是本页的条数，是总条数）
    private String totalcount;

    // 返回角色信息集合
    private Object menulist;

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    public Object getMenulist() {
        return menulist;
    }

    public void setMenulist(Object menulist) {
        this.menulist = menulist;
    }

    // 菜单主菜单
    public static class MenuTree {

        // 菜单id
        private String menuid;

        // 菜单名称
        private String menuname;

        // 菜单url
        private String url;

        // 菜单类型
        private String menutype;

        // 排序号
        private String sort;

        // 菜单描述
        private String remark;

        // 子菜单列表（如果没有子菜单，该字段不存在；如果有子菜单，该字段存在）
        private List<MenuSonTree>menulist;

        // 菜单权限
        private String power;

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<MenuSonTree> getMenulist() {
            return menulist;
        }

        public void setMenulist(List<MenuSonTree> menulist) {
            this.menulist = menulist;
        }

        @Override
        public String toString() {
            return "MenuTree{" +
                    "menuid='" + menuid + '\'' +
                    ", menuname='" + menuname + '\'' +
                    ", url='" + url + '\'' +
                    ", menutype='" + menutype + '\'' +
                    ", sort='" + sort + '\'' +
                    ", remark='" + remark + '\'' +
                    ", menulist=" + menulist +
                    '}';
        }
    }
    // 菜单子菜单
    public static class MenuSonTree extends MenuTree {

        // 菜单id
        private String menuid;

        // 菜单名称
        private String menuname;

        // 菜单url
        private String url;

        // 菜单类型
        private String menutype;

        // 排序号
        private String sort;

        // 菜单描述
        private String remark;

        // 菜单权限
        private String power;

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @Override
        public String toString() {
            return "MenuSonTree{" +
                    "menuid='" + menuid + '\'' +
                    ", menuname='" + menuname + '\'' +
                    ", url='" + url + '\'' +
                    ", menutype='" + menutype + '\'' +
                    ", sort='" + sort + '\'' +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MenuResponse{" +
                "draw='" + draw + '\'' +
                ", totalcount='" + totalcount + '\'' +
                ", menulist=" + menulist +
                '}';
    }
}
