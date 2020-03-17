package com.frank.mswl.ac.user;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端用户添加，修改请求报文.
 *
 * @author 张明亮 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/08 张明亮 创建.
 */
public class UserRequest {

    // 用户登录名
    private String userid;

    // 用户登录名数组
    private String[] useridlist;

    // 用户姓名
    private String username;

    // 用户性别 0:男，1：女
    private String sex;

    // 手机
    private String mobile;

    // 电话
    private String phone;

    // 电子邮箱
    private String mail;

    // 所属机构代码
    private String organid;

    // 角色代码列表（可多选，多个角色代码之间用逗号隔开）
    private String[] rolelist;

    // 备注
    private String remark;

    // 用户头像url
    private String image;

    // 出生日期
    private String birthday;

    // 当前页码，如果等于空，表示不分页
    private String currentpage;

    // 开始检索index
    private String startindex;

    // 每页显示条数，如果等于空，表示不分页
    private String pagesize;

    // 请求次数
    private String draw;

    // 模版名称
    private String templatename;

    // 模版路径
    private String templateurl;

    //修改密码用
    //原密码
    private String oldpassword;

    //新密码
    private String newpassword;

    //重置密码用
    // 密码
    private String password;

    public String getTemplateurl() {
        return templateurl;
    }

    public void setTemplateurl(String templateurl) {
        this.templateurl = templateurl;
    }

    public String getTemplatename() {
        return templatename;
    }

    public void setTemplatename(String templatename) {
        this.templatename = templatename;
    }

    public String[] getUseridlist() {
        return useridlist;
    }

    public void setUseridlist(String[] useridlist) {
        this.useridlist = useridlist;
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

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
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

    public String getOrganid() {
        return organid;
    }

    public void setOrganid(String organid) {
        this.organid = organid;
    }

    //    public String getItem() {
//        return item;
//    }
//
//    public void setItem(String item) {
//        this.item = item;
//    }

    public String[] getRolelist() {
        return rolelist;
    }

    public void setRolelist(String[] rolelist) {
        this.rolelist = rolelist;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", organid='" + organid + '\'' +
                ", rolelist='" + rolelist + '\'' +
                ", remark='" + remark + '\'' +
                ", image='" + image + '\'' +
                ", birthday='" + birthday + '\'' +
                ", currentpage='" + currentpage + '\'' +
                ", startindex='" + startindex + '\'' +
                ", pagesize='" + pagesize + '\'' +
                ", draw='" + draw + '\'' +
                ", oldpassword='" + oldpassword + '\'' +
                ", newpassword='" + newpassword + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
