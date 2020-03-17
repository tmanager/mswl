package com.frank.mswl.ac.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端用户登录controller.
 *
 * @author 张明亮 2019/08/12.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/12 张明亮 创建.
 */
@Slf4j
@RequestMapping("/web")
@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 管理系统用户登录验密.
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestBody String requestData) {

        WebRequest<LoginRequest> loginRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<LoginRequest>>() {
        });
        log.info("web登录验密---------->传入的参数为：{}", requestData);
        return loginService.login(loginRequest);
    }

    /**
     * 验证用户登录是否过期.
     *
     * @return
     */
    @RequestMapping("/overdue")
    public String overdue(@RequestParam String userid) {
        log.info("web验证用户登录是否过期---------->传入的参数为：{}", userid);
        return loginService.overdue(userid);
    }
    /**
     * 管理系统退出.
     *
     * @return null
     */
    @RequestMapping("/logout")
    public String logout(@RequestBody String requestData) {

        WebRequest<LoginRequest> loginRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<LoginRequest>>() {
        });
        log.info("web退出登录---------->传入的参数为：{}", requestData);
        return loginService.logout(loginRequest);
    }
    /**
     * 背对背获取token.
     *
     * @return token
     */
    @RequestMapping("/front/tokenquery")
    public String tokenquery(@RequestBody String requestData) {

        WebRequest<LoginRequest> loginRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<LoginRequest>>() {
        });
        log.info("web背对背获取token---------->传入的参数为：{}", requestData);
        return loginService.tokenquery(loginRequest);
    }
}
