package com.frank.mswl.mini.suggest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.request.WebRequest;
import com.frank.mswl.core.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/suggest")
public class SuggestController {

    @Autowired
    private SuggestService suggestService;

    /**
     * 提交建议.
     */
    @RequestMapping("/send")
    public String send(@RequestBody String requestParam, HttpServletRequest request) {
        log.info("小程序提交建议开始............");

        log.info("请求参数为：{}", requestParam);
        WebRequest<SuggestRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<SuggestRequest>>() {
        });

        String ipAddr = RequestUtil.getIpAddress(request);
        log.info("获取的IP为：[{}]", ipAddr);
        requestData.getRequest().setIpaddr(ipAddr);

        // 调用服务
        String responseData = this.suggestService.sendService(requestData.getRequest());
        log.info("返回小程序提交建议的数据为:\n{}", responseData);

        log.info("小程序端提交建议结束............");
        return responseData;
    }
}
