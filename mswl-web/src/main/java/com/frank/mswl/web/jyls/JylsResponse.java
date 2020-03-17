package com.frank.mswl.web.jyls;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class JylsResponse extends BaseResponse {

    // 交易流水列表
    private List<Map<String, String>> jylslist;
}
