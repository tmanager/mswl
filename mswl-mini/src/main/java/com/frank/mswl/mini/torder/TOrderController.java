package com.frank.mswl.mini.torder;

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
 * 上程序查重列表Controller.
 *
 * @author 张孝党 2020/01/07.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/07. 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/torder")
public class TOrderController {

    @Autowired
    private TOrderService tOrderService;

    /**
     * 小程序端查重订单列表查询.
     */
    @RequestMapping("/query")
    public String query(@RequestBody String requestParam) {
        log.info("小程序端查重订单列表查询开始............");

        log.info("请求参数为：{}", requestParam);
        WebRequest<TOrderRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<TOrderRequest>>() {
        });

        // 调用服务
        String responseData = this.tOrderService.queryService(requestData.getRequest());
        log.info("返回小程序查重订单列表查询的数据为:\n{}", responseData);

        log.info("小程序端查重订单列表查询结束............");
        return responseData;
    }

    /**
     * 小程序端查重订单列表删除.
     */
    @RequestMapping("/del")
    public String delete(@RequestBody String requestParam) {
        log.info("小程序端语法检测订单列表删除开始............");

        log.info("请求参数为：{}", requestParam);
        WebRequest<TOrderRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<TOrderRequest>>() {
        });

        // 调用服务
        String responseData = this.tOrderService.deleteService(requestData.getRequest());
        log.info("返回小程序语法检测订单列表删除的数据为:\n{}", responseData);

        log.info("小程序端语法检测订单列表删除结束............");
        return responseData;
    }
}
