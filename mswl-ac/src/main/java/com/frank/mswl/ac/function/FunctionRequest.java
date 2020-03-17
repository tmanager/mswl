package com.frank.mswl.ac.function;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端功能信息请求报文.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */
public class FunctionRequest {

    // 功能代码
    private String functionid;

    // 功能代码
    private String[] functionidlist;

    // 功能名称
    private String functionname;

    // 父菜单代码
    private String menuid;

    // 功能代码
    private String functioncode;

    // 功能描述
    private String remark;

    private String userid;

    private String roleid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String[] getFunctionidlist() {
        return functionidlist;
    }

    public void setFunctionidlist(String[] functionidlist) {
        this.functionidlist = functionidlist;
    }

    public String getFunctioncode() {
        return functioncode;
    }

    public void setFunctioncode(String functioncode) {
        this.functioncode = functioncode;
    }

    public String getFunctionid() {
        return functionid;
    }

    public void setFunctionid(String functionid) {
        this.functionid = functionid;
    }

    public String getFunctionname() {
        return functionname;
    }

    public void setFunctionname(String functionname) {
        this.functionname = functionname;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FunctionRequest{" +
                "functionid='" + functionid + '\'' +
                ", functionname='" + functionname + '\'' +
                ", menuid='" + menuid + '\'' +
                ", functioncode='" + functioncode + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
