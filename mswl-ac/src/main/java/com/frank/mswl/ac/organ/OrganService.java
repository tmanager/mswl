package com.frank.mswl.ac.organ;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frank.mswl.core.request.WebRequest;
import com.frank.mswl.core.response.SysErrResponse;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.DateTimeUtil;
import com.frank.mswl.core.utils.SdyfJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * web端机构信息操作service.
 *
 * @author 张明亮 2019/08/10.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/08/10 张明亮 创建.
 */

@Service
public class OrganService {

    @Autowired
    private OrganRepository organRepository;

    /**
     * 机构查询接口实现.
     *
     * @return Json字符串数据
     */
    public String organQuery(WebRequest<OrganRequest> requestData) {
        //获取报文体
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        if (requestData.getRequest().getStartindex() != null && !"".equals(requestData.getRequest().getStartindex()) && requestData.getRequest().getPagesize() != null && !"".equals(requestData.getRequest().getPagesize())) {
            //为防止分页查询出错，把这俩个属性强转为int类型
            map.put("startindex", Integer.parseInt(requestData.getRequest().getStartindex()));
            map.put("pagesize", Integer.parseInt(requestData.getRequest().getPagesize()));
            map.put("pagingOrNot", "1");
        }
        //查询所有的机构信息（包含父机构和子机构）
        List<Map<String, Object>> lstData = organRepository.organQueryLists(map);
        // 构建机构树
        HashMap<String, Object> organlist = new HashMap<String, Object>();
        this.getJgTree(lstData, "", organlist);
        //创建接收对象
        WebResponse<OrganResponse> web = new WebResponse<>();
        OrganResponse organResponse = new OrganResponse();
        if (organlist.size() == 0) {
            organResponse.setOrganlist("");
        } else {
            organResponse.setOrganlist(organlist.get("organlist"));
        }
        organResponse.setDraw(requestData.getRequest().getDraw());
        organResponse.setTotalcount(String.valueOf(organRepository.number()));
        web.setResponse(organResponse);
        return JSONObject.toJSON(web).toString();
    }

    /**
     * 机构添加接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String organAdd(WebRequest<OrganRequest> requestData) {
        //对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        HashMap<String, Object> hashMap = new HashMap<>();
        // 查询机构名称是否存在
        map.put("uuid", CommonUtil.getUUid());
        map.put("organcode", CommonUtil.getUUid());
        map.put("addTime", DateTimeUtil.getTimeformat());
        map.put("operator", requestData.getUserid());
        //机构类型 1父机构2子机构
        if (requestData.getRequest().getParentorganid() != null && !"".equals(requestData.getRequest().getParentorganid())) {
            hashMap.put("parentorganid", requestData.getRequest().getParentorganid());
            hashMap.put("organname", requestData.getRequest().getOrganname());
            hashMap.put("type", "2");
            int number = organRepository.organOnly(hashMap);
            if (number <= 0) {
                map.put("type", "2");
            } else {
                return new SysErrResponse("您输入的机构名称已存在，请重新输入！").toJsonString();
            }
        } else {
            hashMap.put("organname", requestData.getRequest().getOrganname());
            hashMap.put("type", "1");
            int number = organRepository.organOnly(hashMap);
            if (number <= 0) {
                map.put("type", "1");
            } else {
                return new SysErrResponse( "您输入的机构名称已存在，请重新输入！").toJsonString();
            }
        }
        organRepository.organAdd(map);
        return new SysResponse().toJsonString();
    }

    /**
     * 机构修改接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String organUpdate(WebRequest<OrganRequest> requestData) {
        //对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        map.put("updateTime", DateTimeUtil.getTimeformat());
        map.put("operator", requestData.getUserid());
        String uname = organRepository.organNameById(requestData.getRequest().getOrganid());
        if (uname.equals(requestData.getRequest().getOrganname())) {
            organRepository.organUpdate(map);
            return new SysResponse().toJsonString();
        } else {
            int number = organRepository.organOnly(map);
            if (number == 0) {
                organRepository.organUpdate(map);
                return new SysResponse().toJsonString();
            } else {
                return new SysErrResponse("您输入的机构名称已存在，请重新输入！").toJsonString();
            }
        }

    }

    /**
     * 机构删除接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String organDelete(WebRequest<OrganRequest> requestData) {
        //批量删除  通过，分割  循环
        List<String> organidlist = requestData.getRequest().getOrganidlist();
        //通过循环查询是否存在子机构，事项，岗位，用户.如果存在，记录在number中
        String number = "";
        for (int i = 0; i < organidlist.size(); i++) {
            int num = organRepository.sonOrgan(organidlist.get(i));
            if (num != 0) {
                if (number.equals("")) {
                    number = (i + 1) + "";
                } else {
                    number = "," + (i + 1);
                }
            }
        }
        if (number.equals("")) {
            for (int i = 0; i < organidlist.size(); i++) {
                organRepository.organDelete(organidlist.get(i));
            }
            return new SysResponse().toJsonString();
        } else {
            return new SysErrResponse("您选中的第" + number + "条数据存在子机构或用户，无法删除，请重新选择！").toJsonString();
        }
    }


    /**
     * 构建机构树.
     *
     * @param lstData 原始数据.
     * @param pid     上级节点ID.
     * @return 机构树.
     */
    private void getJgTree(List<Map<String, Object>> lstData, String pid, Map<String, Object> treeData) {

        List<Map<String, Object>> childrenData = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : lstData) {
            if (map.get("fjdid").toString().equals(pid)) {
                childrenData.add(map);
            }
        }

        if (childrenData.size() != 0) {
            treeData.put("organlist", childrenData);
            for (Map<String, Object> m : childrenData) {
                getJgTree(lstData, m.get("organid").toString(), m);
            }
        }
    }

}
