package com.frank.mswl.core.utils;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * json格式类.
 *
 * @author 张明亮 2019/06/12.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/06/12. 张明亮 创建.
 */
public class SdyfJsonUtil {

//    public static AppRequest AtoRequestObject(String requestData) {
//
//        JSONObject jsonObject = JSONObject.parseObject(requestData);
//        return JSON.toJavaObject(jsonObject, AppRequest.class);
//    }
//
//    public static AppResponse AppResponse(HashMap<String, Object> responseData) {
//
//        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(responseData));
//        return JSON.toJavaObject(jsonObject, AppResponse.class);
//    }

    public static LinkedHashMap Head(String transid, String retcode, String retmsg) {
        LinkedHashMap map = new LinkedHashMap();
        map.put("transid", transid);
        map.put("retcode", retcode);
        map.put("retmsg", retmsg);
        return map;
    }

    /**
     * 对象转map.
     *
     * @return map集合
     */
    public static <T> HashMap beanToMap(T bean) {
        HashMap map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }
}


