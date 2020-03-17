package com.frank.mswl.web.order;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class OrderResponse extends BaseResponse {

    // 订单列表
    private List<Map<String, String>> orderlist;
}
