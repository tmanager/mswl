package com.frank.mswl.mini.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.frank.mswl.core.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小程序首页Service.
 *
 * @author 张孝党 2019/12/25.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/25. 张孝党 创建.
 */
@Slf4j
@Service
public class IndexService {

    @Autowired
    private IndexRepository indexRepository;

    /**
     * 首页数据查询.
     */
    public String queryService() {

        // 查询广告数据
        List<Map<String, String>> advertDataList = this.indexRepository.getAdvertList();
        log.info("查询出的广告数据为：{}", advertDataList);

        // 查询特色服务数据
        List<Map<String, String>> serviceDataList = this.indexRepository.getServiceList();
        log.info("查询出的特色服务数据为：{}", serviceDataList);

        // 查询新人专区数据
        List<Map<String, String>> newBornDataList = this.indexRepository.getNewbornList();
        log.info("查询出的新人专区数据为：{}", newBornDataList);

        // 查询海外招募数据
        List<Map<String, String>> abroadDataList = this.indexRepository.getAbroadList();
        log.info("查询出的海外招募数据为：{}", abroadDataList);

        // 返回报文
        WebResponse<IndexResponse> responseData = new WebResponse<>();
        IndexResponse indexResponse = new IndexResponse();
        responseData.setResponse(indexResponse);

        // 广告
        indexResponse.setAdlist(advertDataList);
        // 特色服务
        indexResponse.setServlist(serviceDataList);
        // 新人专区
        indexResponse.setNewbornlist(newBornDataList);
        // 海外招募
        indexResponse.setAbroadlist(abroadDataList);

        // 返回
        log.info("返回的数据为：{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));
        return JSON.toJSONString(responseData);
    }

    /**
     * 首页推荐阅读数据查询.
     */
    public String articleQueryService(IndexRequest requestData) {

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");

        // 查询结果
        List<Map<String, String>> dataList = this.indexRepository.getArticleList();
        log.info("查询出的结果为：{}", dataList);

        // 查询推荐阅读文章数量
        int cnt = this.indexRepository.getArticlCnt();
        log.info("查询出的数据条数为：{}", cnt);

        // 返回报文
        WebResponse<IndexResponse> responseData = new WebResponse<>();
        IndexResponse indexResponse = new IndexResponse();
        responseData.setResponse(indexResponse);

        indexResponse.setArtlist(dataList);
        indexResponse.setTotalcount(cnt);

        // 返回
        log.info("返回的数据为：{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));
        return JSON.toJSONString(responseData);
    }
}
