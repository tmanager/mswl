package com.frank.mswl.ac.role;

import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端角色信息返回报文.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */
public class RoleResponse {

    // 请求次数
    private String draw;

    // 角色总条数（不是本页的条数，是总条数）
    private String totalcount;

    //返回角色信息集合
    private List<RoleListResponse> rolelist;

    public List<RoleListResponse> getRolelist() {
        return rolelist;
    }

    public void setRolelist(List<RoleListResponse> rolelist) {
        this.rolelist = rolelist;
    }

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

    public static class RoleListResponse{

        // 角色id
        private String roleid;

        // 角色名称
        private String rolename;

        // 角色描述
        private String remark;

        // 操作人
        private String operator;

        // 操作时间
        private String operatetime;

        public String getRoleid() {
            return roleid;
        }

        public void setRoleid(String roleid) {
            this.roleid = roleid;
        }

        public String getRolename() {
            return rolename;
        }

        public void setRolename(String rolename) {
            this.rolename = rolename;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getOperatetime() {
            return operatetime;
        }

        public void setOperatetime(String operatetime) {
            this.operatetime = operatetime;
        }

        @Override
        public String toString() {
            return "RoleListResponse{" +
                    "roleid='" + roleid + '\'' +
                    ", rolename='" + rolename + '\'' +
                    ", remark='" + remark + '\'' +
                    ", operator='" + operator + '\'' +
                    ", operatetime='" + operatetime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RoleResponse{" +
                "draw='" + draw + '\'' +
                ", totalcount='" + totalcount + '\'' +
                ", RoleList=" + rolelist +
                '}';
    }
}
