package com.frank.mswl.mini.coupon;

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
 * 上程序端优惠券Controller.
 *
 * @author 张孝党 2020/01/06.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/06. 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 小程序端个人优惠券查询.
     */
    @RequestMapping("/query")
    public String queryCoupon(@RequestBody String requestParam) {
        log.info("小程序端查询个人优惠券开始............");

        log.info("请求参数为：{}", requestParam);
        WebRequest<CouponRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<CouponRequest>>() {
        });

        // 调用服务
        String responseData = this.couponService.queryCouponService(requestData.getRequest());
        log.info("返回小程序端查询个人优惠券的数据为:\n{}", responseData);

        log.info("小程序端查询个人优惠券结束............");
        return responseData;
    }

    /**
     * 优惠券转赠.
     */
    @RequestMapping("/given")
    public String given(@RequestBody String requestParam) {
        log.info("小程序端优惠券转赠开始............");

        log.info("请求参数为：{}", requestParam);
        WebRequest<CouponRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<CouponRequest>>() {
        });

        // 调用服务
        String responseData = this.couponService.givenService(requestData.getRequest());
        log.info("返回小程序端优惠券转赠的数据为:\n{}", responseData);

        log.info("小程序端优惠券转赠结束............");
        return responseData;
    }
}
