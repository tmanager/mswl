package com.frank.mswl.ac.regulate;

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
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 参数信息Controller.
 *
 * @author 何楠 2019/08/017.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/017 何楠 创建.
 */
@Slf4j
@RequestMapping("/web/front")
@RestController
@CrossOrigin(origins = "*")
public class RegulateController {

    @Autowired
    private RegulateService regulateService;

    /**
     * 参数查询;.
     *
     * @return String字符串;
     */
    @RequestMapping("/regquery")
    public String regulatequery(@RequestBody String requestData) {
        WebRequest<RegulateRequest> regulateRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RegulateRequest>>() {
        });
        log.info("参数查询---------->传入的参数为：{}", requestData);
        return regulateService.regulateQueryList(regulateRequest);
    }

    /**
     * 参数添加.
     *
     * @return String字符串;a
     */
    @RequestMapping("/regadd")
    public String regualteadd(@RequestBody String requestData) {
        WebRequest<RegulateRequest> regulateRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RegulateRequest>>() {
        });
        log.info("参数添加---------->传入的参数为：{}", requestData);
        return regulateService.regulateadd(regulateRequest);
    }

    /**
     * 参数修改.
     *
     * @return String字符串;
     */
    @RequestMapping("/regedit")
    public String regualteUpdate(@RequestBody String requestData) {
        WebRequest<RegulateRequest> regulateRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RegulateRequest>>() {
        });
        log.info("参数修改---------->传入的参数为：{}", requestData);
        return regulateService.regulateUpdate(regulateRequest);
    }

    /**
     * 参数删除.
     *
     * @return String字符串;
     */
    @RequestMapping("/regdelete")
    public String retulatedelete(@RequestBody String requestData) {
        WebRequest<RegulateRequest> regulateRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<RegulateRequest>>() {
        });
        log.info("参数删除---------->传入的参数为：{}", requestData);
        return regulateService.regulateDelete(regulateRequest);
    }
}
