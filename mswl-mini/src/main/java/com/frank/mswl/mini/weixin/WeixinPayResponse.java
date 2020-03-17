package com.frank.mswl.mini.weixin;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class WeixinPayResponse extends BaseResponse {

    // 时间戳
    private String timestamp = "";

    // 随机字符串
    private String noncestr = "";

    // package
    private String paypackage = "";

    // paySign
    private String paysign = "";

    // 消费记录
    private List<Map<String, String>> consumelist;
}
