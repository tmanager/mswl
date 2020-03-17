package com.frank.mswl.web.abroad;

import com.frank.mswl.core.response.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 海外招聘管理请求报文.
 *
 * @author 张孝党 2019/12/23.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/23 张孝党 创建.
 */
@Getter
@Setter
public class AbroadRequest extends BaseRequest {

    // id
    private String abroadid = "";

    // 标题
    private String title = "";

    // 内容
    private String content = "";

    // 删除的招募信息id列表
    private String[] abroadidlist;
}
