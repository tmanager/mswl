package com.frank.mswl.web.feature;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 特色服务管理返回报文.
 *
 * @author 张孝党 2019/12/23.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/23 张孝党 创建.
 */
@Getter
@Setter
public class FeatureReponse extends BaseResponse {

    // 数据列表
    private List<Map<String, String>> servlist;

    // 广告名称
    private String servname = "";

    // 发布时间
    private String time = "";

    // 发布人
    private String editor = "";

    // 图片
    private String servimage = "";

    // 广告类型
    private String servtype = "";

    // 广告内容
    private String content = "";

    // 排序号
    private int sort = 0;

    // 图片外部链接
    private String servlink = "";
}
