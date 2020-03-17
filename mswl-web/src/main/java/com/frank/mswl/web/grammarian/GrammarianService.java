package com.frank.mswl.web.grammarian;

import com.alibaba.fastjson.JSONObject;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * grammarly参数Service.
 *
 * @author 张孝党 2019/12/12.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/12 张孝党 创建.
 */
@Slf4j
@Service
public class GrammarianService {

    @Autowired
    private GrammarianRepository grammarianRepository;

    /**
     * 获取grammarly参数.
     */
    public String getParamService() {

        GrammarianResponse response = new GrammarianResponse();

        List<Map<String, String>> dataList = this.grammarianRepository.getGrammarlyParam();
        log.info(">>>>>>>>>>>>>>>>>>{}", dataList.size());

        // 将数据存储到json中
        dataList.forEach(
                data -> {
                    // 用户名
                    if (data.get("dict_key").equals("gr_uname")) {
                        response.setUname(data.get("dict_value"));
                    }
                    // 密码
                    if (data.get("dict_key").equals("gr_passwd")) {
                        response.setPassword(data.get("dict_value"));
                    }
                }
        );

        WebResponse<GrammarianResponse> webResponse = new WebResponse<>();
        webResponse.setResponse(response);
        // 返回
        return JSONObject.toJSONString(webResponse);
    }

    /**
     * 更新grammarly参数.
     */
    @Transactional(rollbackFor = Exception.class)
    public String updParamService(GrammarianRequest requestData) {

        // 更新
        this.updDict("gr_uname", requestData.getUname());
        this.updDict("gr_passwd", requestData.getPassword());

        return new SysResponse().toJsonString();
    }

    /**
     * 执行更新操作.
     */
    private void updDict(String key, String value) {
        Map<String, String> param = new HashMap<>();
        // 用户名
        param.put("key", key);
        param.put("value", value);
        this.grammarianRepository.updGrammarlyParam(param);
    }
}
