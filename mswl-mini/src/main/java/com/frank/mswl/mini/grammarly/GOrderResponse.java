package com.frank.mswl.mini.grammarly;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GOrderResponse extends BaseResponse {

    // 待支付列表
    private List<Map<String, String>> orderlist;

    // 已支付列表
    private List<Map<String,String>> paylist;

    // 原始价格
    private String price = "";

    // 字数
    private String wordnum = "";

    // 折扣
    private String discount = "";
}
