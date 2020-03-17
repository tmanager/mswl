package com.frank.mswl.ac.regulate;

import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 服务商信息返回报文.
 *
 * @author 张山 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/08 张山 创建.
 */

public class RegulateResponse {

    // 请求次数
    private String draw;

    // 总条数（不是本页的条数，是总条数）
    private String totalcount;

    // 参数列表
    private List<RegulateResponseList> reglist;

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

    public List<RegulateResponseList> getReglist() {
        return reglist;
    }

    public void setReglist(List<RegulateResponseList> reglist) {
        this.reglist = reglist;
    }

    public static class RegulateResponseList {

        //参数id
        private String id;

        //参数名称
        private String regname;

        //参数内容
        private String parameter;

        //参数备注
        private String remark;

        //状态0：启用；1：停止
        private String state;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRegname() {
            return regname;
        }

        public void setRegname(String regname) {
            this.regname = regname;
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
    }
}
