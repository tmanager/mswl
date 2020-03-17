package com.frank.mswl.ac.menu;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端菜单信息请求报文.
 *
 * @author 张明亮 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/08 张明亮 创建.
 */
public class MenuRequest {

    // 菜单id
    private String menuid;

    // 菜单id
    private String[] menuidlist;

    // 菜单code
    private String menucode;
    // 菜单名称
    private String menuname;

    // 菜单url
    private String url;

    // 菜单类型（0：模块，1：页面）
    private String menutype;

    // 排序号
    private String sort;

    // 父菜单代码
    private String parentmenuid;

    // 菜单描述
    private String remark;

    // 当前页码，如果等于空，表示不分页
    private String currentpage;

    // 开始检索index
    private String startindex;

    // 每页显示条数，如果等于空，表示不分页
    private String pagesize;

    // 请求次数
    private String draw;

    // 图标
    private String menuicon;

    public String[] getMenuidlist() {
        return menuidlist;
    }

    public void setMenuidlist(String[] menuidlist) {
        this.menuidlist = menuidlist;
    }

    public String getMenuicon() {
        return menuicon;
    }

    public void setMenuicon(String menuicon) {
        this.menuicon = menuicon;
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

    public String getParentmenuid() {
        return parentmenuid;
    }

    public void setParentmenuid(String parentmenuid) {
        this.parentmenuid = parentmenuid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(String currentpage) {
        this.currentpage = currentpage;
    }

    public String getStartindex() {
        return startindex;
    }

    public void setStartindex(String startindex) {
        this.startindex = startindex;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }

    @Override
    public String toString() {
        return "MenuRequest{" +
                "menuid='" + menuid + '\'' +
                ", menuname='" + menuname + '\'' +
                ", url='" + url + '\'' +
                ", menutype='" + menutype + '\'' +
                ", sort='" + sort + '\'' +
                ", parentmenuid='" + parentmenuid + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
