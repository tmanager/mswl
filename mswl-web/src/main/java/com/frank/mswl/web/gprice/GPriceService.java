package com.frank.mswl.web.gprice;

import com.alibaba.fastjson.JSONObject;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * grammarly价格设置Service.
 *
 * @author 张孝党 2019/12/25.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/25 张孝党 创建.
 */
@Slf4j
@Service
public class GPriceService {

    @Autowired
    private GPriceRepository gPriceRepository;

    /**
     * 获取价格.
     */
    public String getParamService() {

        GPriceResponse response = new GPriceResponse();

        Map<String, String> dataMap = this.gPriceRepository.getGprice();
        log.info(">>>>>>>>>>>>>>>>>>{}", dataMap);

        DecimalFormat df1 = new DecimalFormat("#.00");
        response.setPrice(String.valueOf(df1.format(Double.valueOf(dataMap.get("price")) / 100)));
        response.setWordnum(dataMap.get("wordnum"));

        DecimalFormat df2 = new DecimalFormat("#.0");
        response.setDiscount(String.valueOf(df2.format(Double.valueOf(dataMap.get("discount")) / 10)));

        WebResponse<GPriceResponse> webResponse = new WebResponse<>();
        webResponse.setResponse(response);
        // 返回
        return JSONObject.toJSONString(webResponse);
    }

    /**
     * 更新价格.
     */
    @Transactional(rollbackFor = Exception.class)
    public String updParamService(GPriceRequest requestData) {

        Map<String, String> param = new HashMap<>();
        DecimalFormat df1 = new DecimalFormat("#");
        param.put("price", String.valueOf(df1.format(Double.valueOf(requestData.getPrice()) * 100)));
        param.put("wordnum", requestData.getWordnum());

        DecimalFormat df2 = new DecimalFormat("#");
        param.put("discount", String.valueOf(df1.format(Double.valueOf(requestData.getDiscount()) * 10)));

        int cnt = this.gPriceRepository.updGprice(param);
        log.info("更新查重价格返回值为：[{}]", cnt);

        // 返回
        return new SysResponse().toJsonString();
    }
}
