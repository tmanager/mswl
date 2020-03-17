package com.frank.mswl.web.feature;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 特色服务管理请求报文.
 *
 * @author 张孝党 2019/12/23.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/23 张孝党 创建.
 */
@Getter
@Setter
public class FeatureRequest extends BaseRequest {

    // id
    private String servid = "";

    // 服务名称
    private String servname = "";

    // 外部链接
    private String servlink = "";

    // 排序号
    private int sort = 0;

    // 图片
    private String servimage = "";

    // 服务内容
    private String article = "";

    // 类型
    private String servtype = "";

    // 旧图片
    private String oldservimage = "";

    // 删除的服务id列表
    private String[] servidlist;
}
