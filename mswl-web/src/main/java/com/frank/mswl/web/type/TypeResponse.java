package com.frank.mswl.web.type;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 二手物品，家政服务，房屋出租分类管理返回报文.
 *
 * @author 张孝党 2020/03/19.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/03/19 张孝党 创建.
 */
@Getter
@Setter
public class TypeResponse extends BaseResponse {

    // 数据列表
    private List<Map<String, String>> typelist;
}
