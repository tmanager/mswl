package com.frank.mswl.web.suggest;

import com.alibaba.fastjson.JSON;
import com.frank.mswl.core.request.WebRequest;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 投诉建议Service.
 *
 * @author 张孝党 2020/01/21.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/21 张孝党 创建.
 */
@Slf4j
@Service
public class SuggestService {

    @Autowired
    private SuggestRepository suggestRepository;

    /**
     * 查询投诉建议一览.
     */
    public String queryService(SuggestRequest requestData) {
        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("phone", requestData.getPhone());
        param.put("starttime", requestData.getStarttime());
        param.put("endtime", requestData.getEndtime());
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");

        // 查询结果
        List<Map<String, String>> dataList = this.suggestRepository.getSuggestList(param);
        log.info("查询出的结果为：{}", dataList);
        // 条数
        int cnt = this.suggestRepository.getCnt(param);
        log.info("查询出的数据条数为：{}", cnt);

        WebResponse<SuggestReponse> responseData = new WebResponse<>();
        SuggestReponse suggestReponse = new SuggestReponse();
        suggestReponse.setDraw(0);
        suggestReponse.setTotalcount(cnt);
        suggestReponse.setSuggestlist(dataList);
        responseData.setResponse(suggestReponse);

        return JSON.toJSONString(responseData);
    }

    /**
     * 编辑保存投诉建议.
     */
    @Transactional(rollbackFor = Exception.class)
    public String editSuggestService(WebRequest<SuggestRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // ID
        param.put("id", requestData.getRequest().getId());
        // 标题
        param.put("handle",requestData.getRequest().getHandle());
        // 标题
        param.put("status",requestData.getRequest().getStatus());
        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());
        this.suggestRepository.updSuggest(param);

        // 返回
        return new SysResponse().toJsonString();
    }
}
