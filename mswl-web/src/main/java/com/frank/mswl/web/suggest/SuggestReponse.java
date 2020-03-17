package com.frank.mswl.web.suggest;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 投诉建议返回报文.
 *
 * @author 张孝党 2020/01/21.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2020/01/21 张孝党 创建.
 */
@Getter
@Setter
public class SuggestReponse extends BaseResponse {

    // 数据列表
    private List<Map<String, String>> suggestlist;
}
