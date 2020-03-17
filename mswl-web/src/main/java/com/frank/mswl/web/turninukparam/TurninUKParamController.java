package com.frank.mswl.web.turninukparam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frank.mswl.core.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * UK版turnitin参数Controller.
 *
 * @author 张孝党 2019/12/09.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/09 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/turninuk/param")
public class TurninUKParamController {

    @Autowired
    private TurninUKParamService turninUKParamService;

    /**
     * 查询UK版参数.
     */
    @PostMapping("/query")
    public String getParam() {

        log.info("查询UK版参数开始........................");

        String result = turninUKParamService.getParamService();
        log.info("查询出的UK版参数为：{}", result);

        log.info("查询UK版参数结束........................");

        // 返回
        return result;
    }

    /**
     * 更新国际版参数.
     */
    @PostMapping("/upd")
    public String updParam(@RequestBody String requestData) {
        log.info("更新UK版参数开始........................");

        // 获取请求参数
        WebRequest<TurninUKParamRequest> request = JSON.parseObject(requestData, new TypeReference<WebRequest<TurninUKParamRequest>>() {
        });
        log.info("请求参数为：{}", request);

        String result = turninUKParamService.updParamService(request.getRequest());
        log.info("更新出的UK版参数为：{}", result);

        log.info("更新UK版参数结束........................");

        // 返回
        return result;
    }

}
