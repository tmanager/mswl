package com.frank.mswl.ac.organ;

import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端机构信息返回报文.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */
public class OrganResponse {

    // 请求次数
    private String draw;

    // 总条数（不是本页的条数，是总条数）
    private String totalcount;

    //返回角色信息集合
    private Object organlist;

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

    public Object getOrganlist() {
        return organlist;
    }

    public void setOrganlist(Object organlist) {
        this.organlist = organlist;
    }

    // 主机构树
    public static class OrganTree{

        // 机构id
        private String organid;

        // 机构名称
        private String organname;

        // 排序号（按照排序号进行排序后返回）
        private String sort;

        // 机构负责人
        private String leader;

        // 机构电话
        private String phone;

        // 机构地址
        private String address;

        // 机构描述
        private String remark;

        // 机构代码
        private String organcode;

        // 分区id
        private String areaid;

        // 分区名称
        private String areaname;

        // 子机构列表（如果没有子菜单，该字段不存在；如果有子菜单，该字段存在）
        private List<OrganSonTree> organlist;

        public String getOrgancode() {
            return organcode;
        }

        public void setOrgancode(String organcode) {
            this.organcode = organcode;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getOrganid() {
            return organid;
        }

        public void setOrganid(String organid) {
            this.organid = organid;
        }

        public String getOrganname() {
            return organname;
        }

        public void setOrganname(String organname) {
            this.organname = organname;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getLeader() {
            return leader;
        }

        public void setLeader(String leader) {
            this.leader = leader;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<OrganSonTree> getOrganlist() {
            return organlist;
        }

        public void setOrganlist(List<OrganSonTree> organlist) {
            this.organlist = organlist;
        }

        @Override
        public String toString() {
            return "OrganListResponse{" +
                    "organid='" + organid + '\'' +
                    ", organname='" + organname + '\'' +
                    ", sort='" + sort + '\'' +
                    ", leader='" + leader + '\'' +
                    ", phone='" + phone + '\'' +
                    ", address='" + address + '\'' +
                    ", remark='" + remark + '\'' +
                    ", organlist='" + organlist + '\'' +
                    '}';
        }
    }
    // 子机构树
    public static class OrganSonTree{

        // 机构id
        private String organid;

        // 机构名称
        private String organname;

        // 排序号（按照排序号进行排序后返回）
        private String sort;

        // 机构负责人
        private String leader;

        // 机构电话
        private String phone;

        // 机构地址
        private String address;

        // 机构描述
        private String remark;

        // 机构代码
        private String organcode;

        // 分区id
        private String areaid;

        // 分区名称
        private String areaname;

        public String getOrgancode() {
            return organcode;
        }

        public void setOrgancode(String organcode) {
            this.organcode = organcode;
        }

        public String getRegionid() {
            return areaid;
        }

        public void setRegionid(String regionid) {
            this.areaid = regionid;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getOrganid() {
            return organid;
        }

        public void setOrganid(String organid) {
            this.organid = organid;
        }

        public String getOrganname() {
            return organname;
        }

        public void setOrganname(String organname) {
            this.organname = organname;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getLeader() {
            return leader;
        }

        public void setLeader(String leader) {
            this.leader = leader;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @Override
        public String toString() {
            return "OrganSonTree{" +
                    "organid='" + organid + '\'' +
                    ", organname='" + organname + '\'' +
                    ", sort='" + sort + '\'' +
                    ", leader='" + leader + '\'' +
                    ", phone='" + phone + '\'' +
                    ", address='" + address + '\'' +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrganResponse{" +
                "draw='" + draw + '\'' +
                ", totalcount='" + totalcount + '\'' +
                ", organlist=" + organlist +
                '}';
    }
}
