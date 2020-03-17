package com.frank.mswl.web.newborn;

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
 * 新人专区管理Service.
 *
 * @author 张孝党 2019/12/24.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2019/12/24 张孝党 创建.
 */
@Slf4j
@Service
public class NewBornService {

    @Autowired
    private NewBornRepository newBornRepository;

    /**
     * 查询数据.
     */
    public String queryService(NewBornRequest requestData) {

        // 标题名称
        String title = requestData.getTitle();

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("title", title);
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");

        // 查询结果
        List<Map<String, String>> dataList = this.newBornRepository.getDataList(param);
        log.info("查询出的结果为：{}", dataList);
        // 条数
        int cnt = this.newBornRepository.getCnt(param);
        log.info("查询出的数据条数为：{}", cnt);

        WebResponse<NewBornReponse> responseData = new WebResponse<>();
        NewBornReponse newBornReponse = new NewBornReponse();
        newBornReponse.setDraw(0);
        newBornReponse.setTotalcount(cnt);
        newBornReponse.setNewbornlist(dataList);
        responseData.setResponse(newBornReponse);

        // 返回
        return JSON.toJSONString(responseData);
    }

    /**
     * 新增新人专区信息.
     */
    @Transactional(rollbackFor = Exception.class)
    public String addNewBornService(WebRequest<NewBornRequest> requestData) {

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
        int cnt = this.newBornRepository.addNewBorn(param);
        log.info("新增数据条数为：[{}]", cnt);

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 删除新人专区信息.
     */
    @Transactional(rollbackFor = Exception.class)
    public String delNewBornService(String[] newbornList) {

        for (String newbornid : newbornList) {
            Map<String, String> param = new HashMap<>();
            param.put("id", newbornid);
            this.newBornRepository.deleteNewBorn(param);
            log.info("新人专区信息[{}]被删除", newbornid);
        }

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 编辑保存新人专区信息.
     */
    @Transactional(rollbackFor = Exception.class)
    public String editNewBornService(WebRequest<NewBornRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // ID
        param.put("id", requestData.getRequest().getNewbornid());
        // 标题
        param.put("title", requestData.getRequest().getTitle());
        // 内容
        param.put("article", requestData.getRequest().getContent());
        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());
        // 更新人
        param.put("upduid", requestData.getUserid());
        this.newBornRepository.updNewBorn(param);

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 根据新人专区ID获取新人专区内容.
     */
    public String newBornDetailService(String abrId) {

        // 查询参数
        Map<String, String> param = new HashMap<>();
        param.put("id", abrId);

        // 获取服务详细内容
        Map<String, Object> abroadDetail = this.newBornRepository.getNewBornDetail(param);

        WebResponse<NewBornReponse> responseData = new WebResponse<>();
        NewBornReponse newbornReponse = new NewBornReponse();
        // 标题
        newbornReponse.setTitle(String.valueOf(abroadDetail.get("title")));
        // 发布时间
        newbornReponse.setTime(String.valueOf(abroadDetail.get("updtime")));
        // 发布者
        newbornReponse.setEditor(String.valueOf(abroadDetail.get("uname")));
        // 内容
        newbornReponse.setContent(String.valueOf(abroadDetail.get("article")));
        responseData.setResponse(newbornReponse);

        // 返回
        return JSON.toJSONString(responseData);
    }
}
