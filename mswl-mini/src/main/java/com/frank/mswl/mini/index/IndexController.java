package com.frank.mswl.mini.index;

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
 * 小程序首页Controller.
 *
 * @author 张孝党 2019/12/25.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/25. 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 小程序首页数据加载.
     */
    @RequestMapping(value = "/query")
    public String query() {
        log.info("小程序首页数据查询开始................");

        // 查询数据
        String responseData = this.indexService.queryService();
        log.info("返回小程序的数据为:\n{}", responseData);

        log.info("小程序首页数据查询结束................");

        // 返回
        return responseData;
    }

    /**
     * 小程序首页推荐阅读数据加载.
     */
    @RequestMapping(value = "/article/query")
    public String articleQuery(@RequestBody String requestParam) {
        log.info("小程序首页推荐阅读数据查询开始................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<IndexRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<IndexRequest>>() {
        });

        // 查询数据
        String responseData = this.indexService.articleQueryService(requestData.getRequest());
        log.info("返回小程序推荐阅读的数据为:\n{}", responseData);

        log.info("小程序首页推荐阅读数据查询结束................");

        // 返回
        return responseData;
    }
}
