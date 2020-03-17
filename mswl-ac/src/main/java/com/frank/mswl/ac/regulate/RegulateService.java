package com.frank.mswl.ac.regulate;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frank.mswl.core.request.WebRequest;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.SdyfJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 参数信息service
 *
 * @author 何楠2019/08/17.
 * @versoin V0.0.1.
 * </p>
 * 更新履历：V0.0.1 2019/08/17 何楠 创建
 */
@Service
public class RegulateService {

    @Autowired
    private RegulateRepository regulateRepository;

    /**
     * 参数信息查询接口
     *
     * @return String字符串；
     */
    public String regulateQueryList(WebRequest<RegulateRequest> requestData) {
        //获取报文体
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        if (requestData.getRequest().getStartindex() != null && !"".equals(requestData.getRequest().getStartindex()) && requestData.getRequest().getPagesize() != null && !"".equals(requestData.getRequest().getPagesize())) {
            //为防止分页查询出错，把这俩个属性强转为int类型
            map.put("startindex", Integer.parseInt(requestData.getRequest().getStartindex()));
            map.put("pagesize", Integer.parseInt(requestData.getRequest().getPagesize()));
            map.put("pagingOrNot", "1");
        }
        //创建接收对象
        WebResponse<RegulateResponse> web = new WebResponse<>();
        RegulateResponse regulateResponse = new RegulateResponse();
        regulateResponse.setDraw(requestData.getRequest().getDraw());
        regulateResponse.setTotalcount(String.valueOf(regulateRepository.getNumber(map)));
        regulateResponse.setReglist(regulateRepository.regulateQueryList(map));
        web.setResponse(regulateResponse);
        return JSONObject.toJSON(web).toString();
    }

    /**
     * 参数信息添加接口
     *
     * @return String字符串
     */
    @Transactional(rollbackFor = {Exception.class})
    public String regulateadd(WebRequest<RegulateRequest> requestData) {
        //获取报文体
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        //生成服务商id
        String uid = CommonUtil.getUUid();
        map.put("uid", uid);
        regulateRepository.regulateAdd(map);
        return new SysResponse().toJsonString();
    }

    /**
     * 参数信息删除接口
     *
     * @return String字符串
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public String regulateDelete(WebRequest<RegulateRequest> requestData) {
        //获取报文体
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        List list = requestData.getRequest().getIdlist();
        for (int i = 0; i < list.size(); i++) {
            regulateRepository.regulateDelete(list.get(i).toString());
        }
        return new SysResponse().toJsonString();
    }

    /**
     * 参数信息修改接口.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String regulateUpdate(WebRequest<RegulateRequest> requestData) {
        //对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        regulateRepository.regulateUpdate(map);
        return new SysResponse().toJsonString();
    }
}
