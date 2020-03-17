package com.frank.mswl.web.wxuser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信用户管理Controller.
 *
 * @author 张孝党 2020/01/21.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/21 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/wxuser")
public class WxuserController {
    @Autowired
    private WxuserService wxuserService;

    /**
     * 查询一览.
     */
    @RequestMapping("/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询广告一览开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<WxuserRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<WxuserRequest>>() {
        });

        // 查询
        String responseData = this.wxuserService.queryService(requestData.getRequest());

        log.info("查询广告一览结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }
}
