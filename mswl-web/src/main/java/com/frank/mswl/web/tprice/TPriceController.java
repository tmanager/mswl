package com.frank.mswl.web.tprice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * turnitin价格设置Service.
 *
 * @author 张孝党 2019/12/25.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/25 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tprice")
public class TPriceController {

    @Autowired
    private TPriceService tPriceService;

    /**
     * 查询价格.
     */
    @PostMapping("/query")
    public String getParam() {

        log.info("查询查重价格开始........................");

        String result = tPriceService.getParamService();
        log.info("查询出的查重价格为：{}", result);

        log.info("查询查重价格结束........................");

        // 返回
        return result;
    }

    /**
     * 更新价格.
     */
    @PostMapping("/upd")
    public String updParam(@RequestBody String requestData) {
        log.info("更新查重价格开始........................");

        // 获取请求参数
        WebRequest<TPriceRequest> request = JSON.parseObject(requestData, new TypeReference<WebRequest<TPriceRequest>>() {
        });
        log.info("请求参数为：{}", request);

        String result = this.tPriceService.updParamService(request.getRequest());
        log.info("更新的查重价格返回值为：{}", result);

        log.info("更新查重价格结束........................");

        // 返回
        return result;
    }
}
