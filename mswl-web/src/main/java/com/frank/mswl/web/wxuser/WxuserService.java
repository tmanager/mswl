package com.frank.mswl.web.wxuser;

import com.alibaba.fastjson.JSON;
import com.frank.mswl.core.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信用户管理Service.
 *
 * @author 张孝党 2020/01/21.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/21 张孝党 创建.
 */
@Slf4j
@Service
public class WxuserService {

    @Autowired
    private WxuserRepository wxuserRepository;

    /**
     * 查询数据.
     */
    public String queryService(WxuserRequest requestData) {
        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("nickname", requestData.getNickname());
        param.put("phonenumber", requestData.getPhonenumber());
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");

        // 查询结果
        List<Map<String, String>> dataList = this.wxuserRepository.getWxuserList(param);
        log.info("查询出的结果为：{}", dataList);
        // 条数
        int cnt = this.wxuserRepository.getCnt(param);
        log.info("查询出的数据条数为：{}", cnt);

        WebResponse<WxuserResponse> responseData = new WebResponse<>();
        WxuserResponse wxuserReponse = new WxuserResponse();
        wxuserReponse.setDraw(0);
        wxuserReponse.setTotalcount(cnt);
        wxuserReponse.setWxuserlist(dataList);
        responseData.setResponse(wxuserReponse);

        // 返回
        return JSON.toJSONString(responseData);
    }
}
