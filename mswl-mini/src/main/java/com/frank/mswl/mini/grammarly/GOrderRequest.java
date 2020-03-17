package com.frank.mswl.mini.grammarly;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Getter
@Setter
public class GOrderRequest extends BaseRequest {

    // 用户openid
    @Value("${openid}")
    private String openId = "";

    // 类别，0:待付款，1:已付款
    @Value("${type}")
    private String type = "";

    // orderid列表
    private List<String> orderidlist;
}
