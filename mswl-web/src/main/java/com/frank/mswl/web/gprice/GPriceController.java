package com.frank.mswl.web.gprice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * grammarly价格设置Service.
 *
 * @author 张孝党 2019/12/25.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/25 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/gprice")
public class GPriceController {

    @Autowired
    private GPriceService gPriceService;

    /**
     * 查询价格.
     */
    @PostMapping("/query")
    public String getParam() {

        log.info("查询语法检测价格开始........................");

        String result = gPriceService.getParamService();
        log.info("查询出的语法检测价格为：{}", result);

        log.info("查询语法检测价格结束........................");

        // 返回
        return result;
    }

    /**
     * 更新价格.
     */
    @PostMapping("/upd")
    public String updParam(@RequestBody String requestData) {
        log.info("更新语法检测价格开始........................");

        // 获取请求参数
        WebRequest<GPriceRequest> request = JSON.parseObject(requestData, new TypeReference<WebRequest<GPriceRequest>>() {
        });
        log.info("请求参数为：{}", request);

        String result = this.gPriceService.updParamService(request.getRequest());
        log.info("更新的语法检测价格返回值为：{}", result);

        log.info("更新语法检测价格结束........................");

        // 返回
        return result;
    }
}
