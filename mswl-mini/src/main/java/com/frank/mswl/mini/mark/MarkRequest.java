package com.frank.mswl.mini.mark;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class MarkRequest extends BaseRequest {

    @Value("${openid}")
    private String openId = "";

    @Value("${givenphone}")
    private String givenPhone = "";

    @Value("${givenmark}")
    private String givenMark = "";

    @Value("${coupid}")
    private String coupId = "";
}
