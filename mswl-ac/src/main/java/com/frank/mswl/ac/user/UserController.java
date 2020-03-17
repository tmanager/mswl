package com.frank.mswl.ac.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端用户信息操作controller.
 *
 * @author 张明亮 2019/08/08.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/06/17 张明亮 创建.
 */
@Slf4j
@RequestMapping("/web/front")
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService serService;

    /**
     * 用户添加.
     *
     * @return String字符串
     */
    @RequestMapping("/useradd")
    public String useradd(@RequestBody String requestData) throws IOException {

            WebRequest<UserRequest> userRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<UserRequest>>() {
            });
            log.info("web用户增加---------->传入的参数为：{}", requestData);
            return serService.userAdd(userRequest);
    }

    /**
     * 用户删除.
     *
     * @return String字符串
     */
    @RequestMapping("/userdelete")
    public String userdelete(@RequestBody String requestData) {

        WebRequest<UserRequest> userRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<UserRequest>>() {
        });
        log.info("web用户删除---------->传入的参数为：{}", requestData);
        return serService.userDelete(userRequest);
    }

    /**
     * 用户修改.
     *
     * @return String字符串
     */
    @RequestMapping("/useredit")
    public String useredit(@RequestBody String requestData) {

        WebRequest<UserRequest> userRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<UserRequest>>() {
        });
        log.info("web用户修改---------->传入的参数为：{}", requestData);
        return serService.userUpdate(userRequest);
    }

    /**
     * 用户查询.
     *
     * @return String字符串
     */
    @RequestMapping("/userquery")
    public String userquery(@RequestBody String requestData) {

        WebRequest<UserRequest> userRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<UserRequest>>() {
        });
        log.info("web用户查询---------->传入的参数为：{}", requestData);
        return serService.userQueryList(userRequest);
    }

    /**
     * 密码重置.
     *
     * @return String字符串
     */
    @RequestMapping("/passwordreset")
    public String passwordrest(@RequestBody String requestData) {

        WebRequest<UserRequest> userRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<UserRequest>>() {
        });
        log.info("web密码重置---------->传入的参数为：{}", requestData);
        return serService.passWordRest(userRequest);
    }

    /**
     * 密码修改.
     *
     * @return String字符串
     */
    @RequestMapping("/passwordchange")
    public String passwordchange(@RequestBody String requestData) {

        WebRequest<UserRequest> userRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<UserRequest>>() {
        });
        log.info("web密码修改---------->传入的参数为：{}", requestData);
        return serService.passWordChange(userRequest);

    }
}
