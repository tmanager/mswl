package com.frank.mswl.core.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseResponse {

    // 请求次数
    private int draw = 0;

    // 角色总条数（不是本页的条数，是总条数）
    private int totalcount = 0;
}
