package com.frank.mswl.mini.weixin;

import com.frank.mswl.core.utils.HttpUtil;
import com.frank.mswl.core.utils.SpringUtil;
import com.frank.mswl.mini.config.WeixinMiniConfigBean;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

/**
 * 微信相关操作类.
 *
 * @author 张孝党 2019/12/27.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/27. 张孝党 创建.
 */
@Slf4j
public class WeixinApi {

    private WeixinMiniConfigBean weixinMiniConfigBean;

    public WeixinApi() {
        this.weixinMiniConfigBean = SpringUtil.getBean(WeixinMiniConfigBean.class);
    }

    /**
     * 根据小程序上送的临时code获取用户OpenId.
     */
    public String getAuthCode2Session(String code) {
        log.info("根据小程序上送的临时code获取用户OpenId开始..................");

        // 返回结果
        String result = "";

        try {
            // 请求URL
            String requestUrl = MessageFormat.format(this.weixinMiniConfigBean.getCode2SessionUrl(),
                    this.weixinMiniConfigBean.getAppID(),
                    this.weixinMiniConfigBean.getAppSecret(),
                    code);

            // 发送请求
            result = HttpUtil.getResponseWithGET(requestUrl);
            log.info("小程序获取微信用户openid返回结果为：{}", result);
        } catch (Exception ex) {
            log.error("小程序获取用户openid时异常：\n{}", ex.getMessage());
        }

        log.info("根据小程序上送的临时code获取用户OpenId结束..................");
        // 返回
        return result;
    }
}
