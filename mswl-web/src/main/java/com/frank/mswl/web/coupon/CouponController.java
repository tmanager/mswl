package com.frank.mswl.web.coupon;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.fdfs.FdfsUtil;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 优惠券阅读Controller.
 *
 * @author 张孝党 2020/01/07.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/07 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private FdfsUtil fdfsUtil;

    /**
     * 查询优惠券一览.
     */
    @RequestMapping("/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询优惠券一览开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<CouponRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<CouponRequest>>() {
        });

        String responseData = this.couponService.queryService(requestData.getRequest());

        log.info("查询优惠券一览结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 新增优惠券.
     */
    @RequestMapping(value = "/add")
    private String couponAdd(@RequestBody String requestParam) {
        log.info("优惠券新增开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<CouponRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<CouponRequest>>() {
        });

        // 新增
        String responseData = this.couponService.couponAddService(requestData);
        log.info("优惠券新增结束..................");
        log.info("优惠券新增返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 预览优惠券.
     */
    @RequestMapping(value = "/coupondetail")
    private String couponDetail(@RequestBody String requestParam) {
        log.info("预览优惠券开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<CouponRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<CouponRequest>>() {
        });

        // 预览
        String responseData = this.couponService.couponDetailService(requestData.getRequest());
        log.info("预览优惠券结束..................");
        log.info("预览优惠券返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 删除优惠券.
     */
    @RequestMapping(value = "/delete")
    private String delCoupon(@RequestBody String requestParam) {
        log.info("删除优惠券开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<CouponRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<CouponRequest>>() {
        });

        // 删除
        String responseData = this.couponService.delCouponService(requestData.getRequest().getCoupidlist());
        log.info("删除优惠券结束..................");
        log.info("删除优惠券返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 编辑优惠券.
     */
    @RequestMapping(value = "/edit")
    public String editCoupon(@RequestBody String requestParam) {
        log.info("编辑优惠券开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<CouponRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<CouponRequest>>() {
        });
        String responseData = this.couponService.editCouponService(requestData);
        log.info("优惠券编辑结束..................");
        log.info("优惠券编辑返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 新增优惠券赠送情报.
     */
    @RequestMapping(value = "/give")
    private String couponHisAdd(@RequestBody String requestParam) {
        log.info("优惠券赠送情报新增开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<CouponRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<CouponRequest>>() {
        });

        // 新增
        String responseData = this.couponService.couponHisAddService(requestData);
        log.info("优惠券赠送情报新增结束..................");
        log.info("优惠券赠送情报新增返回值为:{}", responseData);
        return responseData;
    }
}
