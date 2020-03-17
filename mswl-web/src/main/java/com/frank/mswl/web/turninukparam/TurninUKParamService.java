package com.frank.mswl.web.turninukparam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.frank.mswl.core.bean.RequestTurnitinBean;
import com.frank.mswl.core.bean.TurnitinConst;
import com.frank.mswl.core.redis.RedisService;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UK版turnitin参数Service.
 *
 * @author 张孝党 2019/12/09.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/09 张孝党 创建.
 */
@Slf4j
@Service
public class TurninUKParamService {

    @Autowired
    private TurninUKParamRepository turninUKParamRepository;

    @Autowired
    private RedisService redisService;

    /**
     * 获取UK版参数.
     */
    public String getParamService() {

        TurninUKParamResponse response = new TurninUKParamResponse();

        List<Map<String, String>> dataList = this.turninUKParamRepository.getTurnitParam();
        log.info(">>>>>>>>>>>>>>>>>>{}", dataList.size());

        // 将数据存储到json中
        dataList.forEach(
                data -> {
                    // 用户名
                    if (data.get("dict_key").equals("uk_uname")) {
                        response.setUname(data.get("dict_value"));
                    }
                    // 密码
                    if (data.get("dict_key").equals("uk_passwd")) {
                        response.setPassword(data.get("dict_value"));
                    }
                    // classid
                    if (data.get("dict_key").equals("uk_classid")) {
                        response.setClassid(data.get("dict_value"));
                    }
                    // aid
                    if (data.get("dict_key").equals("uk_aid")) {
                        response.setAid(data.get("dict_value"));
                    }
                    // sub aid
                    if (data.get("dict_key").equals("uk_sub_aid")) {
                        response.setSubaid(data.get("dict_value"));
                    }
                    // 论文路径
                    if (data.get("dict_key").equals("uk_thesis_path")) {
                        response.setThesispath(data.get("dict_value"));
                    }
                    // 报告路径
                    if (data.get("dict_key").equals("uk_report_path")) {
                        response.setReportpath(data.get("dict_value"));
                    }
                }
        );

        WebResponse<TurninUKParamResponse> webResponse = new WebResponse<>();
        webResponse.setResponse(response);
        // 返回
        return JSONObject.toJSONString(webResponse);
    }

    /**
     * 更新UK版参数.
     */
    @Transactional(rollbackFor = Exception.class)
    public String updParamService(TurninUKParamRequest requestData) {

        // 更新
        this.updDict("uk_uname", requestData.getUname());
        this.updDict("uk_passwd", requestData.getPassword());
        this.updDict("uk_classid", requestData.getClassid());
        this.updDict("uk_aid", requestData.getAid());
        this.updDict("uk_sub_aid", requestData.getSubaid());
        this.updDict("uk_thesis_path", requestData.getThesispath());
        this.updDict("uk_report_path", requestData.getReportpath());

        // 保存到redis
        if (StringUtils.isEmpty(this.redisService.getStringValue(TurnitinConst.TURN_UK_KEY))) {
            // 删除原有的信息
            this.redisService.removeKey(TurnitinConst.TURN_UK_KEY);
        }
        RequestTurnitinBean bean = new RequestTurnitinBean();
        bean.setUname(requestData.getUname());
        bean.setPasswd(requestData.getPassword());
        bean.setClassid(requestData.getClassid());
        bean.setAid(requestData.getAid());
        bean.setSubAid(requestData.getSubaid());
        bean.setThesisVpnPath(requestData.getThesispath());
        bean.setReportVpnPath(requestData.getReportpath());
        this.redisService.setStringValue(TurnitinConst.TURN_UK_KEY, JSON.toJSONString(bean));
        log.info("保存到redis中UK版的参数为:\n{}", JSON.toJSONString(bean, SerializerFeature.PrettyFormat));

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
        this.turninUKParamRepository.updTurnitParam(param);
    }
}
