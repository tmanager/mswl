package com.frank.mswl.web.article;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 推荐阅读返回报文.
 *
 * @author 张孝党 2019/12/16.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/12/16 张孝党 创建.
 */
@Getter
@Setter
public class ArticleReponse extends BaseResponse {

    // 数据列表
    private List<Map<String, String>> artlist;

    // 标题
    private String title = "";

    // 发布时间
    private String time = "";

    // 发布人
    private String editor = "";

    // 内容
    private String content = "";
}
