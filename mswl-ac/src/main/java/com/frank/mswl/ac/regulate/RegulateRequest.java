package com.frank.mswl.ac.regulate;

import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 参数信息请求报文.
 *
 * @author 何楠 2019/08/017.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/17 何楠 创建.
 */

public class RegulateRequest {

    //参数ID
    private String id;

    //参数名称
    private String regname;

    //参数内容
    private String parameter;

    //参数备注
    private String remark;

    //状态0：启用；1：停止
    private String state;

    // 请求次数
    private String draw;

    // 当前页码，如果等于空，表示不分页
    private String currentpage;

    // 开始检索index
    private String startindex;

    // 每页显示条数，如果等于空，表示不分页
    private String pagesize;

    //参数id集合
    private List idlist;

    public List getIdlist() {
        return idlist;
    }

    public void setIdlist(List idlist) {
        this.idlist = idlist;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegname() {
        return regname;
    }

    public void setRegname(String regname) {
        this.regname = regname;
    }
}