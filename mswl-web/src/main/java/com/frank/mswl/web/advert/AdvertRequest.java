package com.frank.mswl.web.advert;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdvertRequest extends BaseRequest {

    // 广告ID
    private String adid = "";

    // 广告名称
    private String title = "";

    // 预览图片
    private String adurl = "";

    // 广告类型
    private String adtype = "";

    // 文章id
    private String article = "";

    // 排序号
    private String sort = "";

    // 旧图片
    private String oldadimage = "";

    // 删除的广告id列表
    private String[] adidlist;

    // 外部链接url
    private String innerurl = "";
}
