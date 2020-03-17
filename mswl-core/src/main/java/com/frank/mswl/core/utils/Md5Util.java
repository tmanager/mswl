package com.frank.mswl.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * Md5加密格式类.
 *
 * @author 张明亮 2019/06/12.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/06/12. 张明亮 创建.
 *           V0.0.2 2019/12/07  张孝党 增加MD5Encode不带编码参数的方法.
 */
@Slf4j
public class Md5Util {

    private static final String hexDigIts[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * MD5加密.
     *
     * @param origin 明文字符
     * @return 密文
     */
    public static String MD5Encode(String origin) {
        return MD5Encode(origin, "utf-8");
    }

    /**
     * MD5加密
     *
     * @param origin      明文字符
     * @param charsetname 编码
     * @return 密文
     */
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (null == charsetname || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception ex) {
            log.info("md5加密时出错，异常信息：" + ex.getMessage());
        }
        return resultString;
    }


    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }

}


