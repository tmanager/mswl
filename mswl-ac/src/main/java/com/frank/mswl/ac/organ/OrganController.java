package com.frank.mswl.ac.organ;

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
 * web端机构信息操作controller.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */
@Slf4j
@RequestMapping("/web/front")
@RestController
@CrossOrigin(origins = "*")
public class OrganController {

    @Autowired
    private OrganService organService;

    /**
     * 机构查询.
     *
     * @return String字符串
     */
    @RequestMapping("/organquery")
    public String organquery(@RequestBody String requestData) {

        WebRequest<OrganRequest> organRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<OrganRequest>>() {
        });
        log.info("web机构查询---------->传入的参数为：{}", requestData);
        return organService.organQuery(organRequest);
    }

    /**
     * 机构添加.
     *
     * @return String字符串
     */
    @RequestMapping("/organadd")
    public String organadd(@RequestBody String requestData) {

        WebRequest<OrganRequest> organRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<OrganRequest>>() {
        });
        log.info("web机构添加---------->传入的参数为：{}", requestData);
        return organService.organAdd(organRequest);
    }

    /**
     * 机构修改.
     *
     * @return String字符串
     */
    @RequestMapping("/organedit")
    public String organedit(@RequestBody String requestData) {

        WebRequest<OrganRequest> organRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<OrganRequest>>() {
        });
        log.info("web机构修改---------->传入的参数为：{}", requestData);
        return organService.organUpdate(organRequest);
    }

    /**
     * 机构删除.
     *
     * @return String字符串
     */
    @RequestMapping("/organdelete")
    public String organdelete(@RequestBody String requestData) {

        WebRequest<OrganRequest> organRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<OrganRequest>>() {
        });
        log.info("web机构删除---------->传入的参数为：{}", requestData);
        return organService.organDelete(organRequest);
    }


}
