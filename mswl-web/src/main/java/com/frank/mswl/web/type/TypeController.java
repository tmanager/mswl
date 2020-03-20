package com.frank.mswl.web.type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.fdfs.FdfsUtil;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 二手物品，家政服务，房屋出租分类管理Controller.
 *
 * @author 张孝党 2020/03/19.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/03/19 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private FdfsUtil fdfsUtil;

    @Autowired
    private TypeService typeService;

    /**
     * 查询一览.
     */
    @RequestMapping("/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询分类一览开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<TypeRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<TypeRequest>>() {
        });

        // 查询
        String responseData = this.typeService.queryService(requestData.getRequest());

        log.info("查询分类一览结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 新增分类信息.
     */
    @RequestMapping(value = "/add")
    private String addUsedGoods(@RequestBody String requestParam) {
        log.info("分类信息新增开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<TypeRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<TypeRequest>>() {
        });

        // 新增
        String responseData = this.typeService.addTypeService(requestData);
        log.info("分类信息新增结束..................");
        log.info("分类信息新增返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 删除分类信息.
     */
    @RequestMapping(value = "/delete")
    private String delUsedGoods(@RequestBody String requestParam) {
        log.info("删除分类信息开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<TypeRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<TypeRequest>>() {
        });

        // 删除
        String responseData = this.typeService.delTypeService(requestData.getRequest().getTypeidlist());
        log.info("删除分类信息结束..................");
        log.info("删除分类信息返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 编辑分类信息.
     */
    @RequestMapping(value = "/edit")
    public String editUsedGoods(@RequestBody String requestParam) {
        log.info("编辑分类信息开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<TypeRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<TypeRequest>>() {
        });
        String responseData = this.typeService.editTypeService(requestData);
        log.info("分类信息编辑结束..................");
        log.info("分类信息编辑返回值为:{}", responseData);
        return responseData;
    }
}
