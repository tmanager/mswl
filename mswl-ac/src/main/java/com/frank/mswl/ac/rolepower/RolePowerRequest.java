package com.frank.mswl.ac.rolepower;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端角色权限请求报文.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */
public class RolePowerRequest {

    // 角色id
    private String roleid;

    // 有权限菜单id(多个id用逗号分隔）
    private String[] menuidlist;

    // 有权限功能id列表(多个id用逗号分隔）
    private String[] functionidlist;

    // 菜单id
    private String parentmenuid;

    // 菜单id
    private String menuid;


    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getParentmenuid() {
        return parentmenuid;
    }

    public void setParentmenuid(String parentmenuid) {
        this.parentmenuid = parentmenuid;
    }

    public String[] getFunctionidlist() {
        return functionidlist;
    }

    public void setFunctionidlist(String[] functionidlist) {
        this.functionidlist = functionidlist;
    }

    public String[] getMenuidlist() {
        return menuidlist;
    }

    public void setMenuidlist(String[] menuidlist) {
        this.menuidlist = menuidlist;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
}
