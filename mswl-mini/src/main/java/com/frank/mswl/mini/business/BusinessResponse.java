package com.frank.mswl.mini.business;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessResponse extends BaseResponse {

    // 订单号
    private String orderid = "";

    // 论文ID
    private String thesisid = "";

    // 文档大小
    private String filesize = "";

    // 字数
    private String wordcount = "";

    // 字符数
    private String charcount = "";

    // 页数
    private String pagecount = "";

    // 原始价格
    private String price = "";

    // 字数
    private String wordnum = "";

    // 折扣
    private String discount = "";
}
