package com.frank.mswl.web.advert;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 轮播广告管理返回报文.
 *
 * @author 张孝党 2019/12/21.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/21 张孝党 创建.
 */
@Getter
@Setter
public class AdvertResponse extends BaseResponse {

    // 数据列表
    private List<Map<String, String>> adlist;
}
