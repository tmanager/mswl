package com.frank.mswl.mini.login;

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
 * 小程序登录/注册Controller.
 *
 * @author 张孝党 2019/12/27.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/27. 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 账号校验.
     */
    @RequestMapping(value = "/check")
    public String check(@RequestBody String requestParam) {
        log.info("小程序账号校验开始................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<LoginRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<LoginRequest>>() {
        });

        // 查询数据
        String responseData = this.loginService.checkService(requestData.getRequest());
        log.info("返回小程序账号校验的数据为:\n{}", responseData);

        log.info("小程序账号校验查询结束................");

        // 返回
        return responseData;
    }

    /**
     * 更新微信用户信息.
     */
    @RequestMapping(value = "/userinfo")
    public String updUserInfo(@RequestBody String requestParam) {
        log.info("小程序更新用户信息开始................");

        log.info("更新用户信息请求参数为：{}", requestParam);
        WebRequest<LoginRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<LoginRequest>>() {
        });

        // 查询数据
        String responseData = this.loginService.updUserInfoService(requestData.getRequest());
        log.info("返回小程序更新用户信息的数据为:\n{}", responseData);

        log.info("小程序更新用户信息结束................");

        // 返回
        return responseData;
    }

    /**
     * 查询个人积分.
     */
    @RequestMapping(value = "/query/mark")
    public String queryUserMark(@RequestBody String requestParam) {
        log.info("小程序查询个人积分开始................");

        log.info("小程序查询个人积分请求参数为：{}", requestParam);
        WebRequest<LoginRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<LoginRequest>>() {
        });

        // 查询数据
        String responseData = this.loginService.queryUserMarkService(requestData.getRequest().getOpenId());
        log.info("返回小程序查询个人积分的数据为:\n{}", responseData);

        log.info("小程序查询个人积分结束................");

        // 返回
        return responseData;
    }

    /**
     * 查询个人信息.
     */
    @RequestMapping("/user/perinfo")
    public String queryPerInfo(@RequestBody String requestParam) {
        log.info("小程序查询个人信息开始................");

        log.info("小程序查询个人信息请求参数为：{}", requestParam);
        WebRequest<LoginRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<LoginRequest>>() {
        });

        // 查询数据
        String responseData = this.loginService.queryPerInfoService(requestData.getRequest().getOpenId());
        log.info("返回小程序查询个人信息的数据为:\n{}", responseData);

        log.info("小程序查询个人信息结束................");

        // 返回
        return responseData;
    }

    /**
     * 保存个人信息.
     */
    @RequestMapping("/user/saveperinfo")
    public String savePerInfo(@RequestBody String requestParam) {
        log.info("小程序保存个人信息开始................");

        log.info("小程序保存个人信息请求参数为：{}", requestParam);
        WebRequest<LoginRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<LoginRequest>>() {
        });

        // 查询数据
        String responseData = this.loginService.savePerInfoService(requestData.getRequest());
        log.info("返回小程序保存个人信息的数据为:\n{}", responseData);

        log.info("小程序保存个人信息结束................");

        // 返回
        return responseData;
    }
}
