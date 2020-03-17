package com.frank.mswl.web.suggest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.fdfs.FdfsUtil;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 投诉建议Controller.
 *
 * @author 张孝党 2020/01/21.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/21 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/suggest")
public class SuggestController {

    @Autowired
    private SuggestService suggestService;

    @Autowired
    private FdfsUtil fdfsUtil;

    /**
     * 查询投诉建议一览.
     */
    @RequestMapping("/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询投诉建议一览开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<SuggestRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<SuggestRequest>>() {
        });


        String responseData = this.suggestService.queryService(requestData.getRequest());

        log.info("查询投诉建议一览结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 编辑投诉建议.
     */
    @RequestMapping(value = "/edit")
    public String editSuggest(@RequestBody String requestParam) {
        log.info("编辑投诉建议开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<SuggestRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<SuggestRequest>>() {
        });
        String responseData = this.suggestService.editSuggestService(requestData);
        log.info("投诉建议编辑结束..................");
        log.info("投诉建议编辑返回值为:{}", responseData);
        return responseData;
    }
}
