package com.frank.mswl.mini.mark;

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
 * 上程序端积分信息Controller.
 *
 * @author 张孝党 2020/01/08.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/08. 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/mark")
public class MarkController {

    @Autowired
    private MarkService markService;

    /**
     * 小程序端个人积分查询.
     */
    @RequestMapping("/query")
    public String queryCoupon(@RequestBody String requestParam) {
        log.info("小程序端查询个人积分开始............");

        log.info("请求参数为：{}", requestParam);
        WebRequest<MarkRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<MarkRequest>>() {
        });

        // 调用服务
        String responseData = this.markService.queryService(requestData.getRequest());
        log.info("返回小程序端查询个人积分的数据为:\n{}", responseData);

        log.info("小程序端查询个人积分结束............");
        return responseData;
    }

    /**
     * 积分转赠.
     */
    @RequestMapping("/given")
    public String given(@RequestBody String requestParam) {
        log.info("小程序端积分转赠开始............");

        log.info("请求参数为：{}", requestParam);
        WebRequest<MarkRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<MarkRequest>>() {
        });

        // 调用服务
        String responseData = this.markService.givenService(requestData.getRequest());
        log.info("返回小程序端积分转赠的数据为:\n{}", responseData);

        log.info("小程序端积分转赠结束............");
        return responseData;
    }

    /**
     * 查询可兑换的优惠券.
     */
    @RequestMapping("/coupon/query")
    public String couponQuery(@RequestBody String requestParam) {
        log.info("小程序端可用优惠券查询开始............");

        log.info("请求参数为：{}", requestParam);
        WebRequest<MarkRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<MarkRequest>>() {
        });

        // 调用服务
        String responseData = this.markService.couponQueryService(requestData.getRequest());
        log.info("返回小程序端可用优惠券查询的数据为:\n{}", responseData);

        log.info("小程序端可用优惠券查询结束............");
        return responseData;
    }

    /**
     * 兑换.
     */
    @RequestMapping("/coupon/exchange")
    public String couponExchange(@RequestBody String requestParam) {
        log.info("小程序端兑换优惠券开始............");

        log.info("请求参数为：{}", requestParam);
        WebRequest<MarkRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<MarkRequest>>() {
        });

        // 调用服务
        String responseData = this.markService.couponExchangeService(requestData.getRequest());
        log.info("返回小程序端兑换优惠券的数据为:\n{}", responseData);

        log.info("小程序端兑换优惠券结束............");
        return responseData;
    }
}
