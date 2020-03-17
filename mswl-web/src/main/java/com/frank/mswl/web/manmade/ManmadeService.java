package com.frank.mswl.web.manmade;

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
 * 特色服务管理Service.
 *
 * @author 张孝党 2019/12/30.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/30 张孝党 创建.
 */
@Slf4j
@Service
public class ManmadeService {

    @Autowired
    private ManmadeRepository manmadeRepository;

    /**
     * 查询数据.
     */
    public String queryService(ManmadeRequest requestData) {

        // 服务名称
        String title = requestData.getTitle();

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("title", title);
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");

        // 查询结果
        List<Map<String, String>> dataList = this.manmadeRepository.getManmadeList(param);
        log.info("查询出的结果为：{}", dataList);
        // 条数
        int cnt = this.manmadeRepository.getCnt(param);
        log.info("查询出的数据条数为：{}", cnt);


        WebResponse<ManmadeReponse> responseData = new WebResponse<>();
        ManmadeReponse manmadeReponse = new ManmadeReponse();
        manmadeReponse.setDraw(0);
        manmadeReponse.setTotalcount(cnt);
        manmadeReponse.setManmadelist(dataList);
        responseData.setResponse(manmadeReponse);

        // 返回
        return JSON.toJSONString(responseData);
    }

    /**
     * 编辑保存服务.
     */
    @Transactional(rollbackFor = Exception.class)
    public String editManmadeService(WebRequest<ManmadeRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // ID
        param.put("id", requestData.getRequest().getManmadeid());
        // 服务名称
        param.put("title", requestData.getRequest().getTitle());
        // 文章
        param.put("article", requestData.getRequest().getContent());
        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());
        // 更新人
        param.put("upduid", requestData.getUserid());
        this.manmadeRepository.updManmade(param);

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 根据服务ID获取服务内容.
     */
    public String manmadeDetailService(String servId) {

        // 查询参数
        Map<String, String> param = new HashMap<>();
        param.put("id", servId);

        // 获取服务详细内容
        Map<String, Object> manmadeDetail = this.manmadeRepository.getManmadeDetail(param);

        WebResponse<ManmadeReponse> responseData = new WebResponse<>();
        ManmadeReponse manmadeReponse = new ManmadeReponse();
        // 服务名
        manmadeReponse.setTitle(String.valueOf(manmadeDetail.get("title")));
        // 发布时间
        manmadeReponse.setTime(String.valueOf(manmadeDetail.get("updtime")));
        // 发布者
        manmadeReponse.setEditor(String.valueOf(manmadeDetail.get("uname")));
        // 内容
        manmadeReponse.setContent(String.valueOf(manmadeDetail.get("article")));
        responseData.setResponse(manmadeReponse);

        // 返回
        return JSON.toJSONString(responseData);
    }
}
