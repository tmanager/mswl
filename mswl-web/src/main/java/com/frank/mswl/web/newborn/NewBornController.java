package com.frank.mswl.web.newborn;

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
 * 新人专区管理Controller.
 *
 * @author 张孝党 2019/12/24.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/24 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/newborn")
public class NewBornController {

    @Autowired
    private NewBornService newBornService;

    /**
     * 查询一览.
     */
    @RequestMapping("/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询新人专区一览开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<NewBornRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<NewBornRequest>>() {
        });

        // 查询
        String responseData = this.newBornService.queryService(requestData.getRequest());

        log.info("查询新人专区结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 新增新人专区信息.
     */
    @RequestMapping(value = "/add")
    private String addNewBorn(@RequestBody String requestParam) {
        log.info("新人专区信息新增开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<NewBornRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<NewBornRequest>>() {
        });

        // 新增
        String responseData = this.newBornService.addNewBornService(requestData);
        log.info("新人专区信息新增结束..................");
        log.info("新人专区信息新增返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 预览新人专区信息.
     */
    @RequestMapping(value = "/detail")
    private String newBornDetail(@RequestBody String requestParam) {
        log.info("预览新人专区信息开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<NewBornRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<NewBornRequest>>() {
        });

        // 预览
        String responseData = this.newBornService.newBornDetailService(requestData.getRequest().getNewbornid());
        log.info("预览新人专区信息结束..................");
        log.info("预览新人专区信息返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 删除新人专区信息.
     */
    @RequestMapping(value = "/delete")
    private String delNewBorn(@RequestBody String requestParam) {
        log.info("删除新人专区信息开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<NewBornRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<NewBornRequest>>() {
        });

        // 删除
        String responseData = this.newBornService.delNewBornService(requestData.getRequest().getNewbornidlist());
        log.info("删除新人专区信息结束..................");
        log.info("删除新人专区信息返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 编辑新人专区信息.
     */
    @RequestMapping(value = "/edit")
    public String editNewBorn(@RequestBody String requestParam) {
        log.info("编辑新人专区信息开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<NewBornRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<NewBornRequest>>() {
        });
        String responseData = this.newBornService.editNewBornService(requestData);
        log.info("服务新人专区信息结束..................");
        log.info("服务新人专区信息返回值为:{}", responseData);
        return responseData;
    }
}
