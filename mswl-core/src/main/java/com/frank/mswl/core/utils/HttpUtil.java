package com.frank.mswl.core.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 日期时间格式化类.
 *
 * @author 张孝党 2019/06/03.
 * @version V0.0.2.
 * <p>
 * 更新履历： V0.0.1 2019/06/03 张孝党 创建.
 */
public class HttpUtil {

    /**
     * 使用GET方法得到一个URL的数据.
     */
    public static String getResponseWithGET(String pstrUrl) throws Exception {

        // 返回值
        String strResponseBody = "";
        // Http Client
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            // http get
            HttpGet httpget = new HttpGet(pstrUrl);
            // Response
            CloseableHttpResponse response = httpclient.execute(httpget);

            try {
                // 取得返回值
                HttpEntity entity = response.getEntity();
                // 判断返回值
                strResponseBody = entity != null ? EntityUtils.toString(entity, "UTF-8") : "";
            } finally {
                response.close();
            }

        } finally {
            httpclient.close();
        }

        // 返回
        return strResponseBody;
    }

    /**
     * 使用POST方法得到一个URL的数据.
     *
     * @param pstrUrl URL
     * @return 返回值.
     * @throws Exception
     */
    public static String getResponseWithPOST(String pstrUrl) throws Exception {

        // 返回值
        String strResponseBody = "";
        // Http Client
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            // http post
            HttpPost httpPost = new HttpPost(pstrUrl);
            // Response
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                // 取得返回值
                HttpEntity entity = response.getEntity();
                // 判断返回值
                strResponseBody = entity != null ? EntityUtils.toString(entity, "UTF-8") : "";
            } finally {
                response.close();
            }

        } finally {
            httpclient.close();
        }

        // 返回
        return strResponseBody;
    }

    /**
     * 使用POST方法得到一个URL的数据.
     *
     * @param pstrUrl          URL
     * @param pstrPostBodyData POST数据.
     * @return String POST请求返回值.
     * @throws Exception
     */
    public static String getResponseWithPOST(String pstrUrl, String pstrPostBodyData) throws Exception {

        // 返回值
        String strResponseBody = "";
        // Http Client
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            // http post
            HttpPost httpPost = new HttpPost(pstrUrl);

            // POST数据
            StringEntity postEntity = new StringEntity(pstrPostBodyData,
                    ContentType.create("application/x-www-form-urlencoded", "UTF-8"));
            // 设定POST数据
            httpPost.setEntity(postEntity);
            // Response
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                // 取得返回值
                HttpEntity entity = response.getEntity();
                // 判断返回值
                strResponseBody = entity != null ? EntityUtils.toString(entity, "UTF-8") : "";
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }

        // 返回
        return strResponseBody;
    }

    /**
     * 从Request中取得请求的json数据.
     */
    public static String getRequestParam(HttpServletRequest request) throws Exception {

        StringBuilder sb = new StringBuilder();
        String line = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));

        // 按行读取
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        // 返回
        return sb.toString();
    }
}
