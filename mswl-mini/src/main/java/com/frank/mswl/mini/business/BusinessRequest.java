package com.frank.mswl.mini.business;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class BusinessRequest extends BaseRequest {

    // OpenID
    @Value("${openid}")
    private String openId = "";

    // 姓
    @Value("${firstname}")
    private String firstName = "";

    // 名
    @Value("${lastname}")
    private String lastName = "";

    // 标题
    @Value("${subtitle}")
    private String subTitle = "";

    // 原始文件名称
    @Value("${filename}")
    private String orgFileName = "";

    // 检测类型,0:国际查重,1:UK查重,3:语法检测
    @Value("${checktype}")
    private String checkType = "";

    // 订单编号
    @Value("${orderid}")
    private String orderId = "";

    // 上传类型,0文件上传,1:剪贴上传
    @Value("${type}")
    private String type = "";

    /**
     * 论文内容.
     */
    @Value("${content}")
    private String content = "";
}
