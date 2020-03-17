package com.frank.mswl.web.manmade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.fdfs.FdfsUtil;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 人工服务管理Controller.
 *
 * @author 张孝党 2019/12/30.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/30 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/manmade")
public class ManmadeController {

    @Autowired
    private FdfsUtil fdfsUtil;

    @Autowired
    private ManmadeService manmadeService;

    /**
     * 查询一览.
     */
    @RequestMapping("/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询人工服务一览开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<ManmadeRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<ManmadeRequest>>() {
        });

        // 查询
        String responseData = this.manmadeService.queryService(requestData.getRequest());

        log.info("查询人工服务结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 预览服务.
     */
    @RequestMapping(value = "/detail")
    private String manmadeDetail(@RequestBody String requestParam) {
        log.info("预览服务开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<ManmadeRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<ManmadeRequest>>() {
        });

        // 预览
        String responseData = this.manmadeService.manmadeDetailService(requestData.getRequest().getManmadeid());
        log.info("预览服务结束..................");
        log.info("预览服务返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 编辑人工服务.
     */
    @RequestMapping(value = "/edit")
    public String editManmade(@RequestBody String requestParam) {
        log.info("编辑人工服务开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<ManmadeRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<ManmadeRequest>>() {
        });
        String responseData = this.manmadeService.editManmadeService(requestData);
        log.info("人工服务编辑结束..................");
        log.info("人工服务编辑返回值为:{}", responseData);
        return responseData;
    }
}
