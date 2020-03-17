package com.frank.mswl.mini.email;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class EmailRequest extends BaseRequest {

    @Value("${openid}")
    private String opendId = "";

    @Value("${orderid}")
    private String orderId = "";

    @Value("${email}")
    private String eMail = "";

    @Value("${pdfreporturl}")
    private String pdfReportUrl = "";

    @Value("${title}")
    private String title = "";

    @Value("${checktype}")
    private String checkType = "";
}
