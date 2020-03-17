package com.frank.mswl.ac.function;

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
 * web端功能信息操作controller.
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
public class FunctionController {

    @Autowired
    private FunctionService functionService;

    /**
     * 功能查询.
     *
     * @return String字符串
     */
    @RequestMapping("/functionquery")
    public String functionquery(@RequestBody String requestData) {

        WebRequest<FunctionRequest> functionRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<FunctionRequest>>() {
        });
        log.info("web功能查询---------->传入的参数为：{}", requestData);
        return functionService.functionQuery(functionRequest);
    }

    /**
     * 功能添加.
     *
     * @return String字符串
     */
    @RequestMapping("/functionadd")
    public String functionadd(@RequestBody String requestData) {

        WebRequest<FunctionRequest> functionRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<FunctionRequest>>() {
        });
        log.info("web功能添加---------->传入的参数为：{}", requestData);
        return functionService.functionAdd(functionRequest);
    }

    /**
     * 功能修改.
     *
     * @return String字符串
     */
    @RequestMapping("/functionedit")
    public String functionedit(@RequestBody String requestData) {

        WebRequest<FunctionRequest> functionRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<FunctionRequest>>() {
        });
        log.info("web功能修改---------->传入的参数为：{}", requestData);
        return functionService.functionUpdate(functionRequest);
    }

    /**
     * 功能删除.
     *
     * @return String字符串
     */
    @RequestMapping("/functiondelete")
    public String functiondelete(@RequestBody String requestData) {

        WebRequest<FunctionRequest> functionRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<FunctionRequest>>() {
        });
        log.info("web功能删除---------->传入的参数为：{}", requestData);
        return functionService.functionDelete(functionRequest);
    }

}
