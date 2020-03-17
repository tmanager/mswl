package com.frank.mswl.mini.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 小程序配置信息Bean.
 *
 * @author 张孝党 2019/12/27.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/27. 张孝党 创建.
 */
@Getter
@Setter
@Configuration
@PropertySource(value = "classpath:/config/weixin-mini.yml")
@ConfigurationProperties(prefix = "weixin")
public class WeixinMiniConfigBean {

    // AppID
    @Value("${appid}")
    private String appID = "";

    // AppSecret
    @Value("${appsecret}")
    private String appSecret = "";

    // 登录凭证校验 URL
    @Value("${code2Session}")
    private String code2SessionUrl = "";

    @Value("${getaccesstoken}")
    private String accessTokenUrl = "";

    @Value("${getpaidunionId}")
    private String paidUnionId = "";
}
