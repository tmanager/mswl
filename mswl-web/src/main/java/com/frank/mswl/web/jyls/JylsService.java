package com.frank.mswl.web.jyls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.frank.mswl.core.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易流水查询Service.
 *
 * @author 张孝党 2020/01/22.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/22 张孝党 创建.
 */
@Slf4j
@Service
public class JylsService {

    @Autowired
    private JylsRepository jylsRepository;

    /**
     * 交易流水查询.
     */
    public String queryService(JylsRequest requestData) {

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");
        param.put("tradeno", requestData.getTradeNo());
        param.put("phone", requestData.getPhone());
        // 开始日期
        if (!StringUtils.isEmpty(requestData.getStartDate())) {
            param.put("startdate", requestData.getStartDate() + "000000");
        }
        // 结束日期
        if (!StringUtils.isEmpty(requestData.getEndDate())) {
            param.put("enddate", requestData.getEndDate() + "999999");
        }

        // 查询结果
        List<Map<String, String>> dataList = this.jylsRepository.getDataList(param);
        log.info("查询出的结果为：{}", dataList);
        // 条数
        int cnt = this.jylsRepository.getDataCnt(param);
        log.info("查询出的数据条数为：{}", cnt);

        WebResponse<JylsResponse> responseData = new WebResponse<>();
        JylsResponse jylsResponse = new JylsResponse();
        jylsResponse.setDraw(0);
        jylsResponse.setTotalcount(cnt);
        jylsResponse.setJylslist(dataList);
        responseData.setResponse(jylsResponse);
        log.info("交易流水查询返回的数据为：\n", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(responseData, SerializerFeature.WriteMapNullValue);
    }
}
