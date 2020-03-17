package com.frank.mswl.web.manmade;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 人工服务管理返回报文.
 *
 * @author 张孝党 2019/12/30.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/30 张孝党 创建.
 */
@Getter
@Setter
public class ManmadeReponse extends BaseResponse {

    // 数据列表
    private List<Map<String, String>> manmadelist;

    // 广告名称
    private String title = "";

    // 发布时间
    private String time = "";

    // 发布人
    private String editor = "";

    // 广告内容
    private String content = "";
}
