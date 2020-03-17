package com.frank.mswl.web.feature;

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
 * 特色服务管理Controller.
 *
 * @author 张孝党 2019/12/23.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/23 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/feature")
public class FeatureController {

    @Autowired
    private FdfsUtil fdfsUtil;

    @Autowired
    private FeatureService featureService;

    /**
     * 查询一览.
     */
    @RequestMapping("/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询特色服务一览开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<FeatureRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<FeatureRequest>>() {
        });

        // 查询
        String responseData = this.featureService.queryService(requestData.getRequest());

        log.info("查询特色服务结束..................");
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 上传封面图.
     */
    @RequestMapping(value = "/upload/image", headers = "content-type=multipart/form-data")
    public String uploadImg(@RequestParam("image") MultipartFile file, HttpServletRequest request) {
        log.info("上传封面开始.....................");

        JSONObject result = new JSONObject();
        try {
            List<String> imgPath = this.fdfsUtil.uploadImage(file);
            log.info("上传到文件服务器返回的信息为：[{}]", imgPath);

            result.put("ret", "0000");
            result.put("url", imgPath.get(0));
        } catch (Exception ex) {
            log.error("上传缩略图异常:{}", ex.getMessage());
            result.put("ret", "0004");
            result.put("msg", ex.getMessage());
        }

        log.info("上传封面结束.....................");
        // 上传封面结束
        return result.toJSONString();
    }

    /**
     * 新增广告.
     */
    @RequestMapping(value = "/servadd")
    private String addFeature(@RequestBody String requestParam) {
        log.info("服务新增开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<FeatureRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<FeatureRequest>>() {
        });

        // 新增
        String responseData = this.featureService.addFeatureService(requestData);
        log.info("服务新增结束..................");
        log.info("服务新增返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 预览服务.
     */
    @RequestMapping(value = "/servdetail")
    private String featureDetail(@RequestBody String requestParam) {
        log.info("预览服务开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<FeatureRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<FeatureRequest>>() {
        });

        // 预览
        String responseData = this.featureService.featureDetailService(requestData.getRequest().getServid());
        log.info("预览服务结束..................");
        log.info("预览服务返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 删除服务.
     */
    @RequestMapping(value = "/servdelete")
    private String delFeature(@RequestBody String requestParam) {
        log.info("删除服务开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<FeatureRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<FeatureRequest>>() {
        });

        // 删除
        String responseData = this.featureService.delFeatureService(requestData.getRequest().getServidlist());
        log.info("删除服务结束..................");
        log.info("删除服务返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 编辑服务.
     */
    @RequestMapping(value = "/servedit")
    public String editFeature(@RequestBody String requestParam) {
        log.info("编辑服务开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<FeatureRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<FeatureRequest>>() {
        });
        String responseData = this.featureService.editFeatureService(requestData);
        log.info("服务编辑结束..................");
        log.info("服务编辑返回值为:{}", responseData);
        return responseData;
    }
}
