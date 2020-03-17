package com.frank.mswl.web.wxuser;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 微信用户管理返回报文.
 *
 * @author 张孝党 2020/01/21.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/21 张孝党 创建.
 */
@Getter
@Setter
public class WxuserResponse extends BaseResponse {

    // 数据列表
    private List<Map<String, String>> wxuserlist;
}
