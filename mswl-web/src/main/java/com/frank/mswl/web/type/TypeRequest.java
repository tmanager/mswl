package com.frank.mswl.web.type;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeRequest extends BaseRequest {

    // 分类信息ID
    private String typeid = "";

    // 业务分类
    private String busitype = "";

    // 分类名称
    private String typename = "";

    // 排序号
    private String sort = "";

    // 删除的列表
    private String[] typelist;

}
