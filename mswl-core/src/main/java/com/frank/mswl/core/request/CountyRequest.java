package com.frank.mswl.core.request;

import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 县域管理请求报文.
 *
 * @author 张明亮 2019/08/12.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/12 张明亮 创建.
 */

public class CountyRequest {

    // 县域id
    private String countyid;

    // 行政区划代码
    private String provincecode;

    // 行政区划代码
    private String procode;

    // 省名称
    private String proname;

    // 区县名称
    private String countyname;

    // 区县代码
    private String coucode;

    // 服务商id
    private String spid;

    // 行政区域名称
    private String province;

    // 市级行政代码
    private String citycode;

    // 市级名称
    private String city;

    // 市级名称
    private String cityname;

    // 县级列表
    private List county;

    // 县级code集合
    private List countycode;

    // 请求次数
    private String draw;

    // 当前页码，如果等于空，表示不分页
    private String currentpage;

    // 开始检索index
    private String startindex;

    // 每页显示条数，如果等于空，表示不分页
    private String pagesize;

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCountyid() {
        return countyid;
    }

    public void setCountyid(String countyid) {
        this.countyid = countyid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getCountyname() {
        return countyname;
    }

    public void setCountyname(String countyname) {
        this.countyname = countyname;
    }

    public String getCoucode() {
        return coucode;
    }

    public void setCoucode(String coucode) {
        this.coucode = coucode;
    }

    public String getProcode() {
        return procode;
    }

    public void setProcode(String procode) {
        this.procode = procode;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
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

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List getCountycode() {
        return countycode;
    }

    public void setCountycode(List countycode) {
        this.countycode = countycode;
    }


    public List getCounty() {
        return county;
    }

    public void setCounty(List county) {
        this.county = county;
    }
}
