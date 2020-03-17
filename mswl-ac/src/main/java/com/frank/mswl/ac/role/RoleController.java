package com.frank.mswl.ac.role;

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
 * web端角色信息操作controller.
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
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色查询.
     *
     * @return String字符串
     */
    @RequestMapping("/rolequery")
    public String rolequery(@RequestBody String requestData) {

        WebRequest<RoleRequest> roleRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RoleRequest>>() {
        });
        log.info("web角色查询---------->传入的参数为：{}", requestData);
        return roleService.roleQuery(roleRequest);

    }

    /**
     * 角色添加.
     *
     * @return String字符串
     */
    @RequestMapping("/roleadd")
    public String roleadd(@RequestBody String requestData) {

        WebRequest<RoleRequest> roleRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RoleRequest>>() {
        });
        log.info("web角色添加---------->传入的参数为：{}", requestData);
        return roleService.roleAdd(roleRequest);
    }

    /**
     * 角色修改.
     *
     * @return String字符串
     */
    @RequestMapping("/roleedit")
    public String roleedit(@RequestBody String requestData) {

        WebRequest<RoleRequest> roleRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RoleRequest>>() {
        });
        log.info("web角色修改---------->传入的参数为：{}", requestData);
        return roleService.roleUpdate(roleRequest);
    }

    /**
     * 角色删除.
     *
     * @return String字符串
     */
    @RequestMapping("/roledelete")
    public String roledelete(@RequestBody String requestData) {

        WebRequest<RoleRequest> roleRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RoleRequest>>() {
        });
        log.info("web角色删除---------->传入的参数为：{}", requestData);
        return roleService.roleDelete(roleRequest);
    }


}
