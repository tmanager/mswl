package com.frank.mswl.ac.userpower;

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
 * web端用户权限信息操作controller.
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
public class UserPowerController {

    @Autowired
    private UserPowerService userPowerService;

    /**
     * 用户权限查询_全菜单.
     *
     * @return String字符串
     */
    @RequestMapping("/userpowerquery")
    public String userpowerquery(@RequestBody String requestData) {

        WebRequest<UserPowerRequest> userPowerRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<UserPowerRequest>>() {
        });
        log.info("web用户权限查询---------->传入的参数为：{}", requestData);
        return userPowerService.userPowerQuery(userPowerRequest);
    }

    /**
     * 用户权限修改.
     *
     * @return String字符串
     */
    @RequestMapping("/userpoweredit")
    public String userpoweredit(@RequestBody String requestData) {

        WebRequest<UserPowerRequest> userPowerRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<UserPowerRequest>>() {
        });
        log.info("web用户权限修改---------->传入的参数为：{}", requestData);
        return userPowerService.userPowerUpdate(userPowerRequest);
    }

    /**
     * 用户按钮权限菜单查询.
     *
     * @return String字符串
     */
    @RequestMapping("/userfunctionmenuquery")
    public String userfunctionquery(@RequestBody String requestData) {

        WebRequest<UserPowerRequest> userPowerRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<UserPowerRequest>>() {
        });
        log.info("web用户按钮权限菜单查询---------->传入的参数为：{}", requestData);
        return userPowerService.userfunctionQuery(userPowerRequest);
    }

    /**
     * 用户按钮权限查询.
     *
     * @return String字符串
     */
    @RequestMapping("/userpowerfunctionquery")
    public String userpowerfunctionquery(@RequestBody String requestData) {

        WebRequest<UserPowerRequest> userPowerRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<UserPowerRequest>>() {
        });
        log.info("web用户按钮权限菜单查询---------->传入的参数为：{}", requestData);
        return userPowerService.userPowerfunctionQuery(userPowerRequest);
    }

    /**
     * 用户按钮权限修改.
     *
     * @return String字符串
     */
    @RequestMapping("/userfunctionedit")
    public String userfunctionedit(@RequestBody String requestData) {

        WebRequest<UserPowerRequest> userPowerRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<UserPowerRequest>>() {
        });
        log.info("web用户按钮权限菜单修改---------->传入的参数为：{}", requestData);
        return userPowerService.userfunctionUpdate(userPowerRequest);
    }
    /**
     * 用户按钮列表查询.
     *
     * @return String字符串
     */
    @RequestMapping("/userfunctionquerys")
    public String userfunctionquerys(@RequestBody String requestData) {

        WebRequest<UserPowerRequest> userPowerRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<UserPowerRequest>>() {
        });
        log.info("web用户按钮列表查询---------->传入的参数为：{}", requestData);
        return userPowerService.userfunctionquerys(userPowerRequest);
    }

}
