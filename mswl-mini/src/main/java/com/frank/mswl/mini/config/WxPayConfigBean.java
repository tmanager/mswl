package com.frank.mswl.mini.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 小程序微信支付配置信息Bean.
 *
 * @author 张孝党 2020/01/01.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/01. 张孝党 创建.
 */
@Getter
@Setter
@Configuration
@PropertySource(value = "classpath:/config/wxpay.yml")
@ConfigurationProperties(prefix = "wxpay")
public class WxPayConfigBean {

    // AppID
    @Value("${appid}")
    private String appID = "";

    // 商户ID
    @Value("${mch_id}")
    private String mchId = "";

    // 微信支付商户密钥
    @Value("${mch_key}")
    private String mchKey = "";

    // 证书路径
    @Value("${key_path}")
    private String keyPath = "";

    // 异步接收微信支付结果通知的回调地址
    @Value("${notify_url}")
    private String notifyUrl = "";
}
