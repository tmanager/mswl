package com.frank.mswl.web.advert;

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
 * 轮播广告管理Controller.
 *
 * @author 张孝党 2019/12/21.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/21 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/advert")
public class AdvertController {

    @Autowired
    private FdfsUtil fdfsUtil;

    @Autowired
    private AdvertService advertService;

    /**
     * 查询一览.
     */
    @RequestMapping("/query")
    public String query(@RequestBody String requestParam) {
        log.info("查询广告一览开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<AdvertRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<AdvertRequest>>() {
        });

        // 查询
        String responseData = this.advertService.queryService(requestData.getRequest());

        log.info("查询广告一览结束..................");
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
    @RequestMapping(value = "/add")
    private String addAdvert(@RequestBody String requestParam) {
        log.info("广告新增开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<AdvertRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<AdvertRequest>>() {
        });

        // 新增
        String responseData = this.advertService.addAdvertService(requestData);
        log.info("广告新增结束..................");
        log.info("广告新增返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 删除广告.
     */
    @RequestMapping(value = "/delete")
    private String delAdvert(@RequestBody String requestParam) {
        log.info("删除广告开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<AdvertRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<AdvertRequest>>() {
        });

        // 删除
        String responseData = this.advertService.delAdvertService(requestData.getRequest().getAdidlist());
        log.info("删除广告结束..................");
        log.info("删除广告返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 编辑广告.
     */
    @RequestMapping(value = "/edit")
    public String editAdvert(@RequestBody String requestParam) {
        log.info("编辑广告开始..................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<AdvertRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<AdvertRequest>>() {
        });
        String responseData = this.advertService.editAdvertService(requestData);
        log.info("广告编辑结束..................");
        log.info("广告编辑返回值为:{}", responseData);
        return responseData;
    }
}
