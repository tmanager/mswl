package com.frank.mswl.web.advert;

import com.alibaba.fastjson.JSON;
import com.frank.mswl.core.request.WebRequest;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 轮播广告管理Service.
 *
 * @author 张孝党 2019/12/21.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/21 张孝党 创建.
 */
@Slf4j
@Service
public class AdvertService {

    @Autowired
    private AdvertRepository advertRepository;

    /**
     * 查询数据.
     */
    public String queryService(AdvertRequest requestData) {

        // 广告名称
        String adname = requestData.getAdname();

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("adname", adname);
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");

        // 查询结果
        List<Map<String, String>> dataList = this.advertRepository.getAdvertList(param);
        log.info("查询出的结果为：{}", dataList);
        // 条数
        int cnt = this.advertRepository.getCnt(param);
        log.info("查询出的数据条数为：{}", cnt);

        WebResponse<AdvertResponse> responseData = new WebResponse<>();
        AdvertResponse advertReponse = new AdvertResponse();
        advertReponse.setDraw(0);
        advertReponse.setTotalcount(cnt);
        advertReponse.setAdlist(dataList);
        responseData.setResponse(advertReponse);

        // 返回
        return JSON.toJSONString(responseData);
    }

    /**
     * 新增广告.
     */
    @Transactional(rollbackFor = Exception.class)
    public String addAdvertService(WebRequest<AdvertRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // id主键
        param.put("id", CommonUtil.getUUid());
        // 广告名称
        param.put("adname", requestData.getRequest().getAdname());
        // 预览图片
        param.put("adimage", requestData.getRequest().getAdurl());
        // 排序号
        param.put("sort", requestData.getRequest().getSort());
        // 添加时间
        param.put("addTime", DateTimeUtil.getTimeformat());
        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());
        // 操作人
        param.put("operator", requestData.getUserid());

        // 新增
        int cnt = this.advertRepository.addAdvert(param);
        log.info("新增数据条数为：[{}]", cnt);

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 删除文章.
     */
    @Transactional(rollbackFor = Exception.class)
    public String delAdvertService(String[] advertList) {

        for (String advertId : advertList) {
            Map<String, String> param = new HashMap<>();
            param.put("id", advertId);
            this.advertRepository.deleteAdvert(param);
            log.info("广告[{}]被删除", advertId);
        }

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 编辑保存文章.
     */
    @Transactional(rollbackFor = Exception.class)
    public String editAdvertService(WebRequest<AdvertRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // ID
        param.put("id", requestData.getRequest().getAdid());
        // 广告名称
        param.put("adname", requestData.getRequest().getAdname());
        // 图片预览
        if (!requestData.getRequest().getAdurl().equals(requestData.getRequest().getOldadimage())) {
            param.put("adimage", requestData.getRequest().getAdurl());
        }
        // 排序号
        param.put("sort", requestData.getRequest().getSort());
        // 更新时间
        param.put("updateTime", DateTimeUtil.getTimeformat());
        // 更新人
        param.put("operator", requestData.getUserid());

        this.advertRepository.updAdvert(param);

        // 返回
        return new SysResponse().toJsonString();
    }
}
