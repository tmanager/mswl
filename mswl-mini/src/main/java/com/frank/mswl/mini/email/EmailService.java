package com.frank.mswl.mini.email;

import com.alibaba.fastjson.JSONObject;
import com.frank.mswl.core.bean.RequestTurnitinBean;
import com.frank.mswl.core.bean.TurnitinConst;
import com.frank.mswl.core.redis.RedisService;
import com.frank.mswl.core.response.SysErrResponse;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.utils.FileUtils;
import com.frank.mswl.mini.constant.CheckType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 报告发送邮件Service.
 *
 * @author 张孝党 2020/01/12.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/12. 张孝党 创建.
 */
@Slf4j
@Service
public class EmailService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private EmailRepository emailRepository;

    private String LOCAL_TEMP_PATH = "/home/gramtu/tmp";

    /**
     * 发送邮件.
     */
    @Transactional(rollbackFor = Exception.class)
    public String sendService(EmailRequest requestData) {

        String subject = "";

        // 国际版
        if (requestData.getCheckType().equals(CheckType.TURNITIN.getValue())) {
            subject = "turnin查重报告";
        } else if (requestData.getCheckType().equals(CheckType.TURNITINUK.getValue())) {
            // UK版
            subject = "turnin查重报告";
        } else if (requestData.getCheckType().equals(CheckType.GRAMMARLY.getValue())) {
            // Grammarly
            subject = "grammarian语法检测报告";
        } else {
            log.error("报告类型不正确!");
        }

        // 下载PDF报告到本地
        RequestTurnitinBean turnUKBean = JSONObject.parseObject(this.redisService.getStringValue(TurnitinConst.TURN_UK_KEY),
                RequestTurnitinBean.class);
        String pdfReportFile = requestData.getTitle() + "_report.pdf";
        log.info("下载的报告名称为：{}", pdfReportFile);
        boolean downLoad = FileUtils.downloadFromHttpUrl(requestData.getPdfReportUrl(), LOCAL_TEMP_PATH, pdfReportFile);
        if (!downLoad) {
            log.error("从FDFS下载PDF报告时出错!");
            return new SysErrResponse("发送异常,请联系客服!").toJsonString();
        }

        // 发送邮件
        String pdfReportFileFullPath = LOCAL_TEMP_PATH + File.separator + pdfReportFile;
        boolean sendResult = GramtuMailUtil.sendMail(subject, requestData.getEMail(), pdfReportFileFullPath);
        if (!sendResult) {
            log.error("发送邮件时异常!");
            return new SysErrResponse("发送异常,请联系客服!").toJsonString();
        }

        // 将邮箱保存到个人信息中
        Map<String, String> param = new HashMap<>();
        param.put("openid", requestData.getOpendId());
        param.put("email", requestData.getEMail());
        int cnt = this.emailRepository.updEmailByOpenId(param);
        log.info("更新openid[{}]邮箱的结果为[{}]", requestData.getOpendId(), cnt);

        // 返回
        return new SysResponse("发送成功!").toJsonString();
    }
}
