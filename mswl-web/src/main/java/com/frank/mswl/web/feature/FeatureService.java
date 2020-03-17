package com.frank.mswl.web.feature;

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
 * 特色服务管理Service.
 *
 * @author 张孝党 2019/12/23.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/23 张孝党 创建.
 */
@Slf4j
@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    /**
     * 查询数据.
     */
    public String queryService(FeatureRequest requestData) {

        // 服务名称
        String serviceName = requestData.getServname();

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("servname", serviceName);
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");

        // 查询结果
        List<Map<String, String>> dataList = this.featureRepository.getServiceList(param);
        log.info("查询出的结果为：{}", dataList);
        // 条数
        int cnt = this.featureRepository.getCnt(param);
        log.info("查询出的数据条数为：{}", cnt);

        WebResponse<FeatureReponse> responseData = new WebResponse<>();
        FeatureReponse advertReponse = new FeatureReponse();
        advertReponse.setDraw(0);
        advertReponse.setTotalcount(cnt);
        advertReponse.setServlist(dataList);
        responseData.setResponse(advertReponse);

        // 返回
        return JSON.toJSONString(responseData);
    }

    /**
     * 新增特色服务.
     */
    @Transactional(rollbackFor = Exception.class)
    public String addFeatureService(WebRequest<FeatureRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // id主键
        param.put("id", CommonUtil.getUUid());
        // 用户id
        param.put("upduid", requestData.getUserid());
        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());
        // 服务名称
        param.put("servname", requestData.getRequest().getServname());
        // 预览图片
        param.put("servimage", requestData.getRequest().getServimage());
        // 预览图片
        param.put("servtype", requestData.getRequest().getServtype());
        // 服务内容
        param.put("article", requestData.getRequest().getArticle());
        // 排序号
        param.put("sort", String.valueOf(requestData.getRequest().getSort()));
        // 外部链接
        param.put("servlink", requestData.getRequest().getServlink());

        // 新增
        int cnt = this.featureRepository.addFeature(param);
        log.info("新增数据条数为：[{}]", cnt);

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 删除服务.
     */
    @Transactional(rollbackFor = Exception.class)
    public String delFeatureService(String[] featureList) {

        for (String servid : featureList) {
            Map<String, String> param = new HashMap<>();
            param.put("id", servid);
            this.featureRepository.deleteFeature(param);
            log.info("广告[{}]被删除", servid);
        }

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 编辑保存服务.
     */
    @Transactional(rollbackFor = Exception.class)
    public String editFeatureService(WebRequest<FeatureRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // ID
        param.put("id", requestData.getRequest().getServid());
        // 服务名称
        param.put("servname", requestData.getRequest().getServname());
        // 图片预览
        if (!requestData.getRequest().getServimage().equals(requestData.getRequest().getOldservimage())) {
            param.put("servimage", requestData.getRequest().getServimage());
        }
        // 服务类型
        param.put("servtype", requestData.getRequest().getServtype());
        // 文章
        param.put("article", requestData.getRequest().getArticle());
        // 排序号
        param.put("sort", String.valueOf(requestData.getRequest().getSort()));
        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());
        // 更新人
        param.put("upduid", requestData.getUserid());
        // 外部链接
        param.put("servlink", requestData.getRequest().getServlink());
        this.featureRepository.updFeature(param);

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 根据服务ID获取服务内容.
     */
    public String featureDetailService(String servId) {

        // 查询参数
        Map<String, String> param = new HashMap<>();
        param.put("id", servId);

        // 获取服务详细内容
        Map<String, Object> featureDetail = this.featureRepository.getFeatureDetail(param);

        WebResponse<FeatureReponse> responseData = new WebResponse<>();
        FeatureReponse featureReponse = new FeatureReponse();
        // 服务名
        featureReponse.setServname(String.valueOf(featureDetail.get("servname")));
        // 发布时间
        featureReponse.setTime(String.valueOf(featureDetail.get("updtime")));
        // 发布者
        featureReponse.setEditor(String.valueOf(featureDetail.get("uname")));
        // 图片
        featureReponse.setServimage(String.valueOf(featureDetail.get("servimage")));
        // 类型
        featureReponse.setServtype(String.valueOf(featureDetail.get("servtype")));
        // 排序号
        featureReponse.setSort(Integer.parseInt(featureDetail.get("sort").toString()));
        // 内容
        featureReponse.setContent(String.valueOf(featureDetail.get("article")));
        responseData.setResponse(featureReponse);

        // 返回
        return JSON.toJSONString(responseData);
    }
}
