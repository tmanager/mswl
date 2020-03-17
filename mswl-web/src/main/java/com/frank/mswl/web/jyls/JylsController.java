package com.frank.mswl.web.jyls;

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
 * 交易流水查询Controller.
 *
 * @author 张孝党 2020/01/22.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/22 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jyls")
public class JylsController {

    @Autowired
    private JylsService jylsService;

    /**
     * 交易流水查询.
     */
    @RequestMapping("/query")
    public String query(@RequestBody String requestParam) {
        log.info("交易流水开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<JylsRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<JylsRequest>>() {
        });

        String responseData = this.jylsService.queryService(requestData.getRequest());

        log.info("交易流水结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }
}
