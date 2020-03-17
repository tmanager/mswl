package com.frank.mswl.mini.suggest;

import com.frank.mswl.core.response.SysErrResponse;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SuggestService {

    @Autowired
    private SuggestRepository suggestRepository;

    /**
     * 提交建议.
     */
    public String sendService(SuggestRequest requestData) {

        // 校验是否一天内提交超过2次
        Map<String, String> param = new HashMap<>();
        param.put("ipaddr", requestData.getIpaddr());
        param.put("updtime", DateTimeUtil.getCurrentDate());
        int cnt = this.suggestRepository.querySubmitCnt(param);
        log.info("校验是否一天内提交超过2次结果为：[{}]", cnt);
        if (cnt >= 2) {
            return new SysErrResponse("同一账号一天内不能提交超过2次!").toJsonString();
        }

        // 新增
        param.put("id", CommonUtil.getUUid());
        param.put("openid", requestData.getOpenId());
        param.put("question", requestData.getQuestion());
        param.put("phone", requestData.getPhone());
        param.put("updtime", DateTimeUtil.getTimeformat());
        int cnt2 = this.suggestRepository.insSuggest(param);
        log.info("提交结果为：[{}]", cnt2);

        // 返回
        return new SysResponse("提交成功").toJsonString();
    }
}
