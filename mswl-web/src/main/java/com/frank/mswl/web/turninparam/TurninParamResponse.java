package com.frank.mswl.web.turninparam;

import lombok.Getter;
import lombok.Setter;

/**
 * 国际版参数设置返回报文.
 *
 * @author 张孝党 2019/12/09.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/12/09 张孝党 创建.
 */
@Getter
@Setter
public class TurninParamResponse {

    // 用户名
    private String uname;

    // 密码
    private String password;

    // classid
    private String classid;

    // aid
    private String aid;

    // sub aid
    private String subaid;

    // 论文路径
    private String thesispath;

    // 报告路径
    private String reportpath;
}
