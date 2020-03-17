package com.frank.mswl.web.newborn;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 新人专区管理返回报文.
 *
 * @author 张孝党 2019/12/24.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/24 张孝党 创建.
 */
@Getter
@Setter
public class NewBornReponse extends BaseResponse {

    // 数据列表
    private List<Map<String, String>> newbornlist;

    // 标题
    private String title = "";

    // 内容
    private String content = "";

    // 发布人
    private String editor = "";

    // 发布时间
    private String time = "";
}
