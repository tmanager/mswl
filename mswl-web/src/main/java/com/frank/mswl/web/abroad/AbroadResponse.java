package com.frank.mswl.web.abroad;


import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 海外招聘管理请求报文.
 *
 * @author 张孝党 2019/12/23.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/23 张孝党 创建.
 */
@Getter
@Setter
public class AbroadResponse extends BaseResponse {

    private List<Map<String, String>> abroadlist;

    // 招募名称
    private String title = "";

    // 发布时间
    private String time = "";

    // 发布人
    private String editor = "";

    // 招募内容
    private String content = "";
}
