package com.frank.mswl.core.utils;

/**
 * Copyright(C) ShanDongYinFang 2018.
 * <p>
 * 字符串格式化类.
 *
 * @author 张孝党 2018/07/24.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2018/07/24 张孝党 创建.
 */
public class StringFormat {

    /**
     * 模仿C#格式化字符串
     */
    public static String format(String str, String... args) {
        for (int i = 0; i < args.length; i++) {
            str = str.replaceFirst("\\{" + i + "\\}", args[i]);
        }
        return str;
    }
}
