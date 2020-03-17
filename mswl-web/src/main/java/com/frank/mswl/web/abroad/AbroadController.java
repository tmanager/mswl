package com.frank.mswl.web.abroad;

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
 * 海外招聘管理Controller.
 *
 * @author 张孝党 2019/12/23.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/23 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/abroad")
public class AbroadController {

    @Autowired
    private AbroadService abroadService;

    /**
     * 查询一览.
     */
    @RequestMapping("/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询特色服务一览开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<AbroadRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<AbroadRequest>>() {
        });

        // 查询
        String responseData = this.abroadService.queryService(requestData.getRequest());

        log.info("查询特色服务结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 新增招募信息.
     */
    @RequestMapping(value = "/add")
    private String addAbroad(@RequestBody String requestParam) {
        log.info("招募信息新增开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<AbroadRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<AbroadRequest>>() {
        });

        // 新增
        String responseData = this.abroadService.addAbroadService(requestData);
        log.info("招募信息新增结束..................");
        log.info("招募信息新增返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 预览招募信息.
     */
    @RequestMapping(value = "/detail")
    private String abroadDetail(@RequestBody String requestParam) {
        log.info("预览招募信息开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<AbroadRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<AbroadRequest>>() {
        });

        // 预览
        String responseData = this.abroadService.abroadDetailService(requestData.getRequest().getAbroadid());
        log.info("预览招募信息结束..................");
        log.info("预览招募信息返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 删除招募信息.
     */
    @RequestMapping(value = "/delete")
    private String delAbroad(@RequestBody String requestParam) {
        log.info("删除招募信息开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<AbroadRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<AbroadRequest>>() {
        });

        // 删除
        String responseData = this.abroadService.delAbroadService(requestData.getRequest().getAbroadidlist());
        log.info("删除招募信息结束..................");
        log.info("删除招募信息返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 编辑招募信息.
     */
    @RequestMapping(value = "/edit")
    public String editAbroad(@RequestBody String requestParam) {
        log.info("编辑招募信息开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<AbroadRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<AbroadRequest>>() {
        });
        String responseData = this.abroadService.editAbroadService(requestData);
        log.info("服务招募信息结束..................");
        log.info("服务招募信息返回值为:{}", responseData);
        return responseData;
    }
}
