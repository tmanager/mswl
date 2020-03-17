package com.frank.mswl.web.article;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 推荐阅读请求报文.
 *
 * @author 张孝党 2019/12/16.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/12/16 张孝党 创建.
 */
@Getter
@Setter
public class ArticleRequest extends BaseRequest {

    // 文章ID
    private String artid = "";

    // 文章标题
    private String title = "";

    // 封面url
    private String coverimage = "";

    // 文章内容
    private String content = "";

    // 删除的文章id列表
    private String[] artidlist;

    // 旧封面
    private String oldimage = "";
}
