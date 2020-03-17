package com.frank.mswl.mini.util;

import com.frank.mswl.mini.config.WeixinMiniConfigBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Copyright(C) ShanDongYinFang 2018.
 * <p>
 * 微信应用共通方法类.
 *
 * @author 张孝党 2018/09/04.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2018/09/04 张孝党 创建.
 */

@Slf4j
public class WeixinCommonUtil {

    /**
     * 微信加密签名.
     */
    private final String KEY_SIGNATURE = "signature";

    /**
     * 时间戳KEY.
     */
    private final String KEY_TIMESTAMP = "timestamp";

    /**
     * 随机数KEY.
     */
    private final String KEY_NONCE = "nonce";

    @Autowired
    private WeixinMiniConfigBean weixinMiniConfigBean;


    /**
     * 根据token计算signature验证是否为微信服务端发送的消息.
     */
    public boolean checkSignature(HttpServletRequest request) {

        // 微信加密签名
        String strSignature = request.getParameter(KEY_SIGNATURE);
        // 时间戳
        String strTimestamp = request.getParameter(KEY_TIMESTAMP);
        // 随机数
        String strNonce = request.getParameter(KEY_NONCE);

        // tokey
        String strToken = weixinMiniConfigBean.getAppID();
        String strKey = "";
        String strPwd = "";

        if (strSignature != null && strTimestamp != null && strNonce != null) {
            String[] strSet = new String[]{strToken, strTimestamp, strNonce};
            java.util.Arrays.sort(strSet);

            for (String string : strSet) {
                strKey = strKey + string;
            }

            // 进行加密
            strPwd = sha1(strKey);

            // 比较结果
            boolean result = strPwd.equals(strSignature);
            log.info("微信认证返回的比较结果为：" + result);

            return result;
        } else {
            // 不是微信发过来的消息时
            log.info("不是微信服务器发过来的消息");
            return false;
        }
    }

    /**
     * sha1加密算法
     */
    private String sha1(String key) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(key.getBytes());
            // 加密
            String strPwd = new BigInteger(1, md.digest()).toString(16);

            // 返回
            return strPwd;
        } catch (Exception e) {
            // 异常
            e.printStackTrace();
            return key;
        }
    }
}
