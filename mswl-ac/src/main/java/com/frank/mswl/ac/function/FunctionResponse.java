package com.frank.mswl.ac.function;

import java.util.List;
import java.util.Map;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端功能信息返回报文.
 *
 * @author 张明亮 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/08 张明亮 创建.
 */
public class FunctionResponse {

    // 父菜单代码
    private String menuid;

    List<Map<String,Function>> functionlist;

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public List<Map<String, Function>> getFunctionlist() {
        return functionlist;
    }

    public void setFunctionlist(List<Map<String, Function>> functionlist) {
        this.functionlist = functionlist;
    }

    public static class Function {
        // 功能id
        private String functionid;

        // 功能名称
        private String functionname;

        // 功能代码
        private String functioncode;

        // 功能描述
        private String remark;

        // 功能权限
        private String power;

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

        public String getFunctioncode() {
            return functioncode;
        }

        public void setFunctioncode(String functioncode) {
            this.functioncode = functioncode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }
    }

    @Override
    public String toString() {
        return "FunctionResponse{" +
                "menuid='" + menuid + '\'' +
                ", functionlist=" + functionlist +
                '}';
    }
}
