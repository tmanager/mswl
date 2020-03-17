package com.frank.mswl.mini.suggest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class SuggestRequest {

    @Value("${openid}")
    private String openId = "";

    private String question = "";

    private String phone = "";

    private String ipaddr = "";
}
