package com.frank.mswl.ac.userpower;

import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端用户权限请求报文.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */
public class UserPowerRequest {

    // 用户登录名
    private String userid;

    // 有权限的菜单id(多个id用逗号分隔）
    private String[] menuidlist;

    // 有权限功能id列表(多个id用逗号分隔）
    private String[] functionidlist;

    // 菜单id
    private String parentmenuid;

    // 菜单id
    private List menuid;

    public String[] getFunctionidlist() {
        return functionidlist;
    }

    public void setFunctionidlist(String[] functionidlist) {
        this.functionidlist = functionidlist;
    }

    public String getParentmenuid() {
        return parentmenuid;
    }

    public void setParentmenuid(String parentmenuid) {
        this.parentmenuid = parentmenuid;
    }

    public String[] getMenuidlist() {
        return menuidlist;
    }

    public void setMenuidlist(String[] menuidlist) {
        this.menuidlist = menuidlist;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List getMenuid() {
        return menuid;
    }

    public void setMenuid(List menuid) {
        this.menuid = menuid;
    }

    @Override
    public String toString() {
        return "UserPowerRequest{" +
                "userid='" + userid + '\'' +
                ", menuidlist='" + menuidlist + '\'' +
                '}';
    }
}
