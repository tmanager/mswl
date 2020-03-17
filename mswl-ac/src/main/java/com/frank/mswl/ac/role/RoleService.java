package com.frank.mswl.ac.role;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frank.mswl.core.request.WebRequest;
import com.frank.mswl.core.response.SysErrResponse;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.DateTimeUtil;
import com.frank.mswl.core.utils.SdyfJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Random;


/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端角色信息操作service.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 角色查询接口实现.
     *
     * @return Json字符串数据
     */
    public String roleQuery(WebRequest<RoleRequest> requestData) {
        //获取报文体
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        if (requestData.getRequest().getStartindex() != null && !"".equals(requestData.getRequest().getStartindex()) && requestData.getRequest().getPagesize() != null && !"".equals(requestData.getRequest().getPagesize())) {
            //为防止分页查询出错，把这俩个属性强转为int类型
            map.put("startindex", Integer.parseInt(requestData.getRequest().getStartindex()));
            map.put("pagesize", Integer.parseInt(requestData.getRequest().getPagesize()));
            map.put("pagingOrNot", "1");
        }
        //创建接收对象
        WebResponse<RoleResponse> web = new WebResponse<>();
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setRolelist(roleRepository.roleQueryList(map));
        roleResponse.setDraw(requestData.getRequest().getDraw());
        roleResponse.setTotalcount(String.valueOf(roleRepository.number(map)));
        web.setResponse(roleResponse);
        return JSONObject.toJSON(web).toString();
    }

    /**
     * 角色添加接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String roleAdd(WebRequest<RoleRequest> requestData) {
        //对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        int roleOnly = roleRepository.roleOnly(requestData.getRequest().getRolecode());
        if (roleOnly == 0) {
            //code 随机6位数
            Random rand = new Random();
            map.put("uuid", CommonUtil.getUUid());
            map.put("addTime", DateTimeUtil.getTimeformat());
            map.put("operator", requestData.getUserid());
            roleRepository.roleAdd(map);
            return new SysResponse().toJsonString();
        } else {
            return new SysErrResponse( "角色代码已存在，请修改后重试！").toJsonString();
        }
    }

    /**
     * 角色修改接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String roleUpdate(WebRequest<RoleRequest> requestData) {
        //对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        map.put("updateTime", DateTimeUtil.getTimeformat());
        map.put("operator", requestData.getUserid());
        roleRepository.roleUpdate(map);
        return new SysResponse().toJsonString();
    }

    /**
     * 角色删除接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String roleDelete(WebRequest<RoleRequest> requestData) {
        //批量删除  通过，分割  循环
        String number = "";
        List<String> roleidlist = requestData.getRequest().getRoleidlist();
        for (int i = 0; i < roleidlist.size(); i++) {
            int num = roleRepository.roleQueryById(roleidlist.get(i));
            if (num != 0) {
                if (number.equals("")) {
                    number = (i + 1) + "";
                } else {
                    number = "," + (i + 1);
                }
            }
        }
        if (number.equals("")) {
            for (int i = 0; i < roleidlist.size(); i++) {
                roleRepository.roleDelete(roleidlist.get(i));
            }
            return new SysResponse().toJsonString();
        } else {
            return new SysErrResponse("您选中的第" + number + "条数据存在使用中，无法删除，请重新选择！").toJsonString();
        }
    }
}
