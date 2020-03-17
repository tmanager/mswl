package com.frank.mswl.ac.rolepower;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端角色权限信息操作controller.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */
@Slf4j
@RequestMapping("/web/front")
@RestController
@CrossOrigin(origins = "*")
public class RolePowerController {

    @Autowired
    private RolePowerService rolepowerQuery;

    /**
     * 角色权限查询_全菜单.
     *
     * @return String字符串
     */
    @RequestMapping("/rolepowerquery")
    public String rolepowerquery(@RequestBody String requestData) {

        WebRequest<RolePowerRequest> rolePowerRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RolePowerRequest>>() {
        });
        log.info("web角色权限查询---------->传入的参数为：{}", requestData);
        return rolepowerQuery.rolepowerQuery(rolePowerRequest);
    }

    /**
     * 角色权限修改.
     *
     * @return String字符串
     */
    @RequestMapping("/rolepoweredit")
    public String rolepoweredit(@RequestBody String requestData) {

        WebRequest<RolePowerRequest> rolePowerRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RolePowerRequest>>() {
        });
        log.info("web角色权限修改---------->传入的参数为：{}", requestData);
        return rolepowerQuery.rolepowerUpdate(rolePowerRequest);
    }

    /**
     * 角色按钮权限菜单查询.
     *
     * @return String字符串
     */
    @RequestMapping("/rolefunctionmenuquery")
    public String rolefunctionquery(@RequestBody String requestData) {

        WebRequest<RolePowerRequest> rolePowerRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RolePowerRequest>>() {
        });
        log.info("web角色按钮权限菜单查询---------->传入的参数为：{}", requestData);
        return rolepowerQuery.rolefunctionQuery(rolePowerRequest);
    }

    /**
     * 角色按钮权限查询.
     *
     * @return String字符串
     */
    @RequestMapping("/rolepowerfunctionquery")
    public String rolepowerfunctionquery(@RequestBody String requestData) {

        WebRequest<RolePowerRequest> rolePowerRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RolePowerRequest>>() {
        });
        log.info("web角色按钮权限菜单查询---------->传入的参数为：{}", requestData);
        return rolepowerQuery.rolePowerfunctionQuery(rolePowerRequest);
    }

    /**
     * 角色按钮权限修改.
     *
     * @return String字符串
     */
    @RequestMapping("/rolefunctionedit")
    public String rolefunctionedit(@RequestBody String requestData) {

        WebRequest<RolePowerRequest> rolePowerRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RolePowerRequest>>() {
        });
        log.info("web角色按钮权限菜单查询---------->传入的参数为：{}", requestData);
        return rolepowerQuery.rolefunctionUpdate(rolePowerRequest);
    }

}
