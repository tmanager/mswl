package com.frank.mswl.mini.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.frank.mswl.core.bean.RequestTurnitinBean;
import com.frank.mswl.core.bean.TurnitinConst;
import com.frank.mswl.core.fdfs.FdfsUtil;
import com.frank.mswl.core.redis.RedisService;
import com.frank.mswl.core.request.WebRequest;
import com.frank.mswl.core.response.SysErrResponse;
import com.frank.mswl.core.utils.IdGeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;

/**
 * 上传文件及价格计算Controller.
 *
 * @author 张孝党 2020/01/02.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/02. 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/busi")
public class BusinessController {

    @Autowired
    private FdfsUtil fdfsUtil;

    @Autowired
    private RedisService redisService;

    @Autowired
    private BusinessService businessService;

    private static final String LOCAL_TXT_PATH  = "/home/gramtu/tmp/gramtu/thesistxt";

    /**
     * 上传文件.
     */
    @RequestMapping(value = "/upload", headers = "content-type=multipart/form-data")
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        log.info("小程序上传文件开始................");

        String responseData = "";

        // 接收到的参数
        String openId = request.getParameter("openid");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String subTitle = request.getParameter("subtitle");
        String checkType = request.getParameter("checktype");
        String fileName = request.getParameter("filename");
        String uploadType = request.getParameter("type");
        // 论文内容
        String content = request.getParameter("content");
        log.info("接收到的参数为：{}-{}-{},检测类型为：{},上传类型为：{}", firstName, lastName, subTitle, checkType, uploadType);

        BusinessRequest requestData = new BusinessRequest();
        requestData.setOpenId(openId);
        requestData.setFirstName(firstName);
        requestData.setLastName(lastName);
        requestData.setSubTitle(subTitle);
        requestData.setCheckType(checkType);
        requestData.setOrgFileName(fileName);
        requestData.setType(uploadType);

        try {
            String filePath = "";
            if (uploadType.equals("0")) {
                // 上传至FDFS
                filePath = this.fdfsUtil.uploadFile(file);
                log.info("[{}]上传至文件服务器后的路径为[{}]", requestData.getOrgFileName(), filePath);
            } else {
                log.error("上传文档与传入内容不匹配!");
                return new SysErrResponse("上传文档与传入内容不匹配").toJsonString();
            }

            // 调用服务
            responseData = this.businessService.uploadService(requestData, filePath);
            log.info("返回小程序的数据为:\n{}", responseData);
        } catch (Exception ex) {
            log.error("上传文档时异常结束,异常信息：\n{}", ex.getMessage());
            return new SysErrResponse("上传文档时异常结束").toJsonString();
        }

        log.info("小程序上传文件结束................");

        // 返回
        return responseData;
    }

    /**
     * 上传文件内容.
     */
    @RequestMapping("/upload/content")
    public String uploadFileContent(@RequestBody String requestParam) {
        log.info("小程序上传文件内容开始................");

        String responseData = "";

        log.info("请求参数为：{}", requestParam);
        WebRequest<BusinessRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<BusinessRequest>>() {
        });
        log.info("接收到的参数为：{}-{}-{},检测类型为：{},上传类型为：{}",
                requestData.getRequest().getFirstName(),
                requestData.getRequest().getLastName(),
                requestData.getRequest().getSubTitle(),
                requestData.getRequest().getCheckType(),
                requestData.getRequest().getType());

        try {
            String filePath = "";

            // 加载turnitin国际版账户信息
            RequestTurnitinBean turnBean = JSONObject.parseObject(this.redisService.getStringValue(TurnitinConst.TURN_IN_KEY),
                    RequestTurnitinBean.class);
            log.info("查询出的国际版账户信息为：\n{}", JSON.toJSONString(turnBean, SerializerFeature.PrettyFormat));

            // 保存文件到本地
            IdGeneratorUtils id = new IdGeneratorUtils(1, 1);
            String saveFileName = String.valueOf(id.nextId()) + ".txt";
            requestData.getRequest().setOrgFileName(saveFileName);
            log.info("保存的论文文件为：[{}]", saveFileName);
            File localFile = new File(BusinessController.LOCAL_TXT_PATH + File.separator + saveFileName);
            FileWriter fileWriter = new FileWriter(localFile);
            fileWriter.write(requestData.getRequest().getContent());
            fileWriter.flush();
            fileWriter.close();

            // 上传至FDFS
            filePath = this.fdfsUtil.uploadLocalFile(localFile);
            log.info("[{}]上传至文件服务器后的路径为[{}]", requestData.getRequest().getOrgFileName(), filePath);

            // 调用服务
            responseData = this.businessService.uploadService(requestData.getRequest(), filePath);
            log.info("返回小程序的数据为:\n{}", responseData);
        } catch (Exception ex) {
            log.error("上传文档内容时异常结束,异常信息：\n{}", ex.getMessage());
            return new SysErrResponse("上传文档内容时异常结束").toJsonString();
        }

        log.info("小程序上传文件内容结束................");

        // 返回
        return responseData;
    }

    /**
     * 解析论文字数.
     */
    @RequestMapping(value = "/analyse")
    public String analyse(@RequestBody String requestParam) {
        log.info("小程序解析论文字数开始................");

        log.info("请求参数为：{}", requestParam);
        WebRequest<BusinessRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<BusinessRequest>>() {
        });

        // 调用服务
        String responseData = this.businessService.analyseService(requestData.getRequest().getOrderId());
        log.info("返回小程序解析论文字的数据为:\n{}", responseData);

        log.info("小程序解析论文字数结束................");

        // 返回
        return responseData;
    }
}
