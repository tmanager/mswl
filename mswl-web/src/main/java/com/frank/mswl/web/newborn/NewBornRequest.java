package com.frank.mswl.web.newborn;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 新人专区管理返回请求.
 *
 * @author 张孝党 2019/12/24.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/24 张孝党 创建.
 */
@Getter
@Setter
public class NewBornRequest extends BaseRequest {

    // id
    private String newbornid = "";

    // 标题
    private String title = "";

    // 内容
    private String content = "";

    // 删除id列表
    private String[] newbornidlist;
}
