package com.frank.mswl.mini.mark;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MarkResponse extends BaseResponse {

    // 总积分
    private String mark = "0";

    // 积分列表
    private List<Map<String, String>> marklist;

    // 优惠券列表
    private List<Map<String, String>> couponlist;
}
