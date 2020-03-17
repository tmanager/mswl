package com.frank.mswl.core.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseRequest {

    // 当前页码，如果等于空，表示不分页
    private int currentpage = 0;

    // 开始检索index
    private int startindex = 0;

    // 每页显示条数，如果等于空，表示不分页
    private int pagesize = 0;

    // 请求次数
    private int draw = 0;
}
