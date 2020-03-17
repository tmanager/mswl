package com.frank.mswl.web.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.frank.mswl.core.fdfs.FdfsUtil;
import com.frank.mswl.core.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单查询Controller.
 *
 * @author 张孝党 2020/01/14.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/14 张孝党 创建.
 */
@Slf4j
@Service
public class OrderService {

    @Autowired
    private FdfsUtil fdfsUtil;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * 订单一览.
     */
    public String queryService(OrderRequest requestData) {

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");
        param.put("orderid", requestData.getOrderId());
        if (!requestData.getCheckType().equals("-1")) {
            param.put("checktype", requestData.getCheckType());
        }
        if (!requestData.getStatus().equals("-1")) {
            param.put("status", requestData.getStatus());
        }
        param.put("phone", requestData.getPhone());
        // 开始日期
        if (!StringUtils.isEmpty(requestData.getStartDate())) {
            param.put("startdate", requestData.getStartDate() + "000000");
        }
        // 结束日期
        if (!StringUtils.isEmpty(requestData.getEndDate())) {
            param.put("enddate", requestData.getEndDate() + "999999");
        }

        // 查询结果
        List<Map<String, String>> dataList = this.orderRepository.getOrderList(param);
        log.info("查询出的结果为：{}", dataList);
        // 条数
        int cnt = this.orderRepository.getOrderCnt(param);
        log.info("查询出的数据条数为：{}", cnt);

        WebResponse<OrderResponse> responseData = new WebResponse<>();
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setDraw(0);
        orderResponse.setTotalcount(cnt);
        orderResponse.setOrderlist(dataList);
        responseData.setResponse(orderResponse);
        log.info("订单查询返回的数据为：\n", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(responseData, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 上传报告.
     */
    public void uploadService(MultipartFile pdfReport,
                              MultipartFile htmlReport,
                              String orderId,
                              String repetRate) throws Exception {

        // 更新参数
        Map<String, String> param = new HashMap<>();
        param.put("orderid", orderId);

        log.info("上传PDF报告开始..........");
        String pdfUrl = this.fdfsUtil.uploadFile(pdfReport);
        log.info("PDF报告上传后的路径为:[{}]", pdfUrl);
        param.put("pdfreporturl", pdfUrl);

        if (!htmlReport.getOriginalFilename().equals(pdfReport.getOriginalFilename())) {
            log.info("上传HTML报告开始..........");
            String htmlUrl = this.fdfsUtil.uploadFile(htmlReport);
            log.info("HTML报告上传后的路径为:[{}]", pdfUrl);
            param.put("htmlreporturl", htmlUrl);

            param.put("repetrate", repetRate);
        }
        param.put("status", "4");

        // 更新订单状态
        int cnt = this.orderRepository.updOrderReport(param);
        log.info("更新订单报告的结果为：[{}]", cnt);
    }
}
