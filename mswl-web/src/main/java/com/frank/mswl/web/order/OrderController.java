package com.frank.mswl.web.order;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单查询Controller.
 *
 * @author 张孝党 2020/01/14.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/14 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单查询.
     */
    @RequestMapping("/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询订单一览开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<OrderRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<OrderRequest>>() {
        });

        String responseData = this.orderService.queryService(requestData.getRequest());

        log.info("查询订单一览结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 手动上传报告.
     */
    @RequestMapping(value = "/upload", headers = "content-type=multipart/form-data")
    public String upload(@RequestParam("pdfreport") MultipartFile pdfReport, @RequestParam("htmlreport") MultipartFile htmlReport, HttpServletRequest request) {
        log.info("上传报告开始.....................");

        String orderId = request.getParameter("orderid");
        log.info("order id = [{}]", orderId);
        String repetRate = request.getParameter("repetrate");

        JSONObject result = new JSONObject();
        try {
            // 更新
            this.orderService.uploadService(pdfReport, htmlReport, orderId, repetRate);
            result.put("retcode", "0000");
            result.put("retmsg", "更新成功");
        } catch (Exception ex) {
            log.error("上传报告异常:{}", ex.getMessage());
            result.put("retcode", "0004");
            result.put("retmsg", ex.getMessage());
        }

        log.info("上传报告结束.....................");
        // 上传报告结束
        return result.toJSONString();
    }

}
