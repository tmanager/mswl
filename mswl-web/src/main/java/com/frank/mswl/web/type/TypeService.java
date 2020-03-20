package com.frank.mswl.web.type;

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
 * 二手物品，家政服务，房屋出租分类管理Service.
 *
 * @author 张孝党 2020/03/19.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/03/19 张孝党 创建.
 */
@Slf4j
@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    /**
     * 查询数据.
     */
    public String queryService(TypeRequest requestData) {

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("busitype", requestData.getBusitype());
        param.put("typename", requestData.getTypename());
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");

        // 查询结果
        List<Map<String, String>> dataList = this.typeRepository.getTypeList(param);
        log.info("查询出的结果为：{}", dataList);
        // 条数
        int cnt = this.typeRepository.getCnt(param);
        log.info("查询出的数据条数为：{}", cnt);

        WebResponse<TypeResponse> responseData = new WebResponse<>();
        TypeResponse usedGoodsResponse = new TypeResponse();
        usedGoodsResponse.setDraw(0);
        usedGoodsResponse.setTotalcount(cnt);
        usedGoodsResponse.setTypelist(dataList);
        responseData.setResponse(usedGoodsResponse);

        // 返回
        return JSON.toJSONString(responseData);
    }

    /**
     * 新增二手商品交易信息.
     */
    @Transactional(rollbackFor = Exception.class)
    public String addTypeService(WebRequest<TypeRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // id主键
        param.put("id", CommonUtil.getUUid());
        // 业务分类
        param.put("busitype", requestData.getRequest().getBusitype());
        // 分类
        param.put("typename", requestData.getRequest().getTypename());
        // 排序
        param.put("sort", requestData.getRequest().getSort());
        // 添加时间
        param.put("addTime", DateTimeUtil.getTimeformat());
        // 修改时间
        param.put("updateTime", DateTimeUtil.getTimeformat());
        // 操作人
        param.put("operator", requestData.getUserid());

        // 新增
        int cnt = this.typeRepository.addType(param);
        log.info("新增数据条数为：[{}]", cnt);

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 删除文章.
     */
    @Transactional(rollbackFor = Exception.class)
    public String delTypeService(String[] typeidlist) {

        for (String id : typeidlist) {
            Map<String, String> param = new HashMap<>();
            param.put("id", id);
            this.typeRepository.deleteType(param);
            log.info("二手商品[{}]被删除", id);
        }

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 编辑保存文章.
     */
    @Transactional(rollbackFor = Exception.class)
    public String editTypeService(WebRequest<TypeRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // ID
        param.put("id", requestData.getRequest().getTypeid());
        // 分类
        param.put("typename", requestData.getRequest().getTypename());
        // 排序号
        param.put("sort", requestData.getRequest().getSort());
        // 更新时间
        param.put("updateTime", DateTimeUtil.getTimeformat());
        // 更新人
        param.put("operator", requestData.getUserid());
        this.typeRepository.updType(param);

        // 返回
        return new SysResponse().toJsonString();
    }
}
