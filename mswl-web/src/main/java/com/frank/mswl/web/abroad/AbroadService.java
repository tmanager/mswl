package com.frank.mswl.web.abroad;

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
 * 海外招聘管理Service.
 *
 * @author 张孝党 2019/12/23.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/23 张孝党 创建.
 */
@Slf4j
@Service
public class AbroadService {

    @Autowired
    private AbroadRepository abroadRepository;

    /**
     * 查询数据.
     */
    public String queryService(AbroadRequest requestData) {

        // 名称
        String title = requestData.getTitle();

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("title", title);
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");

        // 查询结果
        List<Map<String, String>> dataList = this.abroadRepository.getAbroadList(param);
        log.info("查询出的结果为：{}", dataList);
        // 条数
        int cnt = this.abroadRepository.getCnt(param);
        log.info("查询出的数据条数为：{}", cnt);


        WebResponse<AbroadResponse> responseData = new WebResponse<>();
        AbroadResponse abroadResponse = new AbroadResponse();
        abroadResponse.setDraw(0);
        abroadResponse.setTotalcount(cnt);
        abroadResponse.setAbroadlist(dataList);
        responseData.setResponse(abroadResponse);

        // 返回
        return JSON.toJSONString(responseData);
    }

    /**
     * 新增特色服务.
     */
    @Transactional(rollbackFor = Exception.class)
    public String addAbroadService(WebRequest<AbroadRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // id主键
        param.put("id", CommonUtil.getUUid());
        // 用户id
        param.put("upduid", requestData.getUserid());
        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());
        // 服务名称
        param.put("title", requestData.getRequest().getTitle());
        // 服务内容
        param.put("article", requestData.getRequest().getContent());

        // 新增
        int cnt = this.abroadRepository.addAbroad(param);
        log.info("新增数据条数为：[{}]", cnt);

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 删除招募信息.
     */
    @Transactional(rollbackFor = Exception.class)
    public String delAbroadService(String[] abroadList) {

        for (String abroadid : abroadList) {
            Map<String, String> param = new HashMap<>();
            param.put("id", abroadid);
            this.abroadRepository.deleteAbroad(param);
            log.info("招募信息[{}]被删除", abroadid);
        }

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 编辑保存招募信息.
     */
    @Transactional(rollbackFor = Exception.class)
    public String editAbroadService(WebRequest<AbroadRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // ID
        param.put("id", requestData.getRequest().getAbroadid());
        // 服务名称
        param.put("title", requestData.getRequest().getTitle());
        // 内容
        param.put("article", requestData.getRequest().getContent());
        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());
        // 更新人
        param.put("upduid", requestData.getUserid());
        this.abroadRepository.updAbroad(param);

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 根据招募ID获取招募内容.
     */
    public String abroadDetailService(String abrId) {

        // 查询参数
        Map<String, String> param = new HashMap<>();
        param.put("id", abrId);

        // 获取服务详细内容
        Map<String, Object> abroadDetail = this.abroadRepository.getAbroadDetail(param);

        WebResponse<AbroadResponse> responseData = new WebResponse<>();
        AbroadResponse abroadReponse = new AbroadResponse();
        // 服务名
        abroadReponse.setTitle(String.valueOf(abroadDetail.get("title")));
        // 发布时间
        abroadReponse.setTime(String.valueOf(abroadDetail.get("updtime")));
        // 发布者
        abroadReponse.setEditor(String.valueOf(abroadDetail.get("uname")));
        // 内容
        abroadReponse.setContent(String.valueOf(abroadDetail.get("article")));
        responseData.setResponse(abroadReponse);

        // 返回
        return JSON.toJSONString(responseData);
    }
}
