package com.frank.mswl.ac.user;

import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端用户返回报文.
 *
 * @author 张明亮 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/08 张明亮 创建.
 */
public class UserResponse {

    // 请求次数
    private String draw;

    // 数据总条数
    private String totalcount;

    private List<UserListResponse>Userlist;

    public List<UserListResponse> getUserlist() {
        return Userlist;
    }

    public void setUserlist(List<UserListResponse> userlist) {
        Userlist = userlist;
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

    public static class UserListResponse {
        // 用户登录名
        private String userid;

        // 用户姓名
        private String username;

        // 用户头像
        private String image;

        // 用户性别 0:男，1：女
        private String sex;

        // 手机
        private String mobile;

        // 电话
        private String phone;

        // 电子邮箱
        private String mail;

        // 所属机构名称
        private String organname;

        // 所属机构代码
        private String organid;

        // 所属事项名称
        //private String itemname;

        // 所属事项代码
        //private String itemid;

        // 所属岗位名称
        private String stationname;

        // 所属岗位代码
        private String stationid;

        // 角色名称列表（可多选，多个角色名称之间用逗号隔开）
        private String rolename;

        // 角色代码列表（可多选，多个角色名称之间用逗号隔开）
        private String roleid;

        // 备注
        private String remark;

        // 登录次数
        private String logontimes;

        // 最后登录时间
        private String lastlogontime;

        // 分数
        private String mark;

        // 楼层id
        private String storeyid;

        // 楼层名称
        private String storeyname;

        // 分区id
        private String areaid;

        // 分区名称
        private String areaname;

        // 是否需要评价 0需要1不需要
        private String evaluationneed;


        public String getStoreyname() {
            return storeyname;
        }

        public void setStoreyname(String storeyname) {
            this.storeyname = storeyname;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getStoreyid() {
            return storeyid;
        }

        public void setStoreyid(String storeyid) {
            this.storeyid = storeyid;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getEvaluationneed() {
            return evaluationneed;
        }

        public void setEvaluationneed(String evaluationneed) {
            this.evaluationneed = evaluationneed;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getOrganname() {
            return organname;
        }

        public void setOrganname(String organname) {
            this.organname = organname;
        }

        public String getOrganid() {
            return organid;
        }

        public void setOrganid(String organid) {
            this.organid = organid;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
//        public String getItemname() {
//            return itemname;
//        }
//
//        public void setItemname(String itemname) {
//            this.itemname = itemname;
//        }
//
//        public String getItemid() {
//            return itemid;
//        }
//
//        public void setItemid(String itemid) {
//            this.itemid = itemid;
//        }

        public String getStationname() {
            return stationname;
        }

        public void setStationname(String stationname) {
            this.stationname = stationname;
        }

        public String getStationid() {
            return stationid;
        }

        public void setStationid(String stationid) {
            this.stationid = stationid;
        }

        public String getRolename() {
            return rolename;
        }

        public void setRolename(String rolename) {
            this.rolename = rolename;
        }

        public String getRoleid() {
            return roleid;
        }

        public void setRoleid(String roleid) {
            this.roleid = roleid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getLogontimes() {
            return logontimes;
        }

        public void setLogontimes(String logontimes) {
            this.logontimes = logontimes;
        }

        public String getLastlogontime() {
            return lastlogontime;
        }

        public void setLastlogontime(String lastlogontime) {
            this.lastlogontime = lastlogontime;
        }

        @Override
        public String toString() {
            return "UserListResponse{" +
                    "userid='" + userid + '\'' +
                    ", username='" + username + '\'' +
                    ", sex='" + sex + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", phone='" + phone + '\'' +
                    ", mail='" + mail + '\'' +
                    ", organname='" + organname + '\'' +
                    ", organid='" + organid + '\'' +
                    ", stationname='" + stationname + '\'' +
                    ", stationid='" + stationid + '\'' +
                    ", rolename='" + rolename + '\'' +
                    ", rolenameid='" + roleid + '\'' +
                    ", remark='" + remark + '\'' +
                    ", logontimes='" + logontimes + '\'' +
                    ", lastlogontime='" + lastlogontime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "draw='" + draw + '\'' +
                ", totalcount='" + totalcount + '\'' +
                ", UserList=" + Userlist +
                '}';
    }
}
