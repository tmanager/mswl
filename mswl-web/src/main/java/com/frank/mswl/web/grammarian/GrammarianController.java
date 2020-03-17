package com.frank.mswl.web.grammarian;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * grammarly参数Controller.
 *
 * @author 张孝党 2019/12/12.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/12 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/grammarly/param")
public class GrammarianController {

    @Autowired
    private GrammarianService grammarianService;

    /**
     * 查询grammarly参数.
     */
    @PostMapping("/query")
    public String getParam() {

        log.info("查询grammarly参数开始........................");

        String result = grammarianService.getParamService();
        log.info("查询出的grammarly参数为：{}", result);

        log.info("查询grammarly参数结束........................");

        // 返回
        return result;
    }

    /**
     * 更新grammarly参数.
     */
    @PostMapping("/upd")
    public String updParam(@RequestBody String requestData) {
        log.info("更新grammarly参数开始........................");

        // 获取请求参数
        WebRequest<GrammarianRequest> request = JSON.parseObject(requestData, new TypeReference<WebRequest<GrammarianRequest>>() {
        });
        log.info("请求参数为：{}", request);

        String result = grammarianService.updParamService(request.getRequest());
        log.info("更新出的grammarly参数为：{}", result);

        log.info("更新grammarly参数结束........................");

        // 返回
        return result;
    }

}
