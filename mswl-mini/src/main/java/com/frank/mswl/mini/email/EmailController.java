package com.frank.mswl.mini.email;

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
 * 报告发送邮件Controller.
 *
 * @author 张孝党 2020/01/12.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/12. 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     * 将PDF报告发送邮件.
     * @param requestParam
     * @return
     */
    @RequestMapping("/send")
    public String sendMail(@RequestBody String requestParam) {
        log.info("小程序端发送PDF报告开始............");

        log.info("请求参数为：{}", requestParam);
        WebRequest<EmailRequest> requestData = JSON.parseObject(requestParam, new TypeReference<WebRequest<EmailRequest>>() {
        });

        // 调用服务
        String responseData = this.emailService.sendService(requestData.getRequest());
        log.info("返回小程序发送PDF报告的数据为:\n{}", responseData);

        log.info("小程序端发送PDF报告结束............");
        return responseData;
    }
}
