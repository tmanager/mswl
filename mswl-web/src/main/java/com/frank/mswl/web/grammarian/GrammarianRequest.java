package com.frank.mswl.web.grammarian;

import lombok.Getter;
import lombok.Setter;

/**
 * grammarly参数设置请求报文.
 *
 * @author 张孝党 2019/12/12.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/12/12 张孝党 创建.
 */
@Getter
@Setter
public class GrammarianRequest {

    // 用户名
    private String uname;

    // 密码
    private String password;
}
