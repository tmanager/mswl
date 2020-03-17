package com.frank.mswl.web.portal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.frank.mswl.core.response.SysErrResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页数据查询Service.
 *
 * @author 张孝党 2020/01/18.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/18 张孝党 创建.
 */
@Slf4j
@Service
public class PortalService {

    @Autowired
    private PortalRepository portalRepository;

    /**
     * 首页统计数据查询.
     */
    public String statisticsService() {

        // 返回数据
        WebResponse<PortalResponse> responseData = new WebResponse<>();
        PortalResponse portalResponse = new PortalResponse();
        responseData.setResponse(portalResponse);

        // 查询参数
        Map<String, String> param = new HashMap<>();
        param.put("updtime", DateTimeUtil.getCurrentDate());
        // 今日注册人数
        int todayUser = this.portalRepository.getTodayUserCnt(param);
        portalResponse.setTodayuser(String.valueOf(todayUser));
        // 总注册人数
        int totalUser = this.portalRepository.getTotalUserCnt();
        portalResponse.setTotaluser(String.valueOf(totalUser));
        // 完善信息用户数
        int perUser = this.portalRepository.getPerUserCnt();
        portalResponse.setPeruser(String.valueOf(perUser));

        // 今日订单数
        int todayCount = this.portalRepository.getTodayOrderCnt(param);
        portalResponse.setTodayordercount(String.valueOf(todayCount));
        // 今日订单金额
        int todayAmount = this.portalRepository.getTodayOrderAmount(param);
        portalResponse.setTodayorderamount(String.valueOf(todayAmount));

        // 全部订单数
        int totalCount = this.portalRepository.getTotalOrderCnt(param);
        portalResponse.setTotalordercount(String.valueOf(totalCount));
        // 全部订单金额
        int totalAmount = this.portalRepository.getTotalOrderAmount(param);
        portalResponse.setTotalorderamount(String.valueOf(totalAmount));

        param.put("checktype", "2");
        // 今日Grammarly订单数
        int todayGrammarlyCnt = this.portalRepository.getTodayOrderCnt(param);
        portalResponse.setTodaygramcount(String.valueOf(todayGrammarlyCnt));

        // 今日Grammarly订单金额
        int todayGrammarlyAmout = this.portalRepository.getTodayOrderAmount(param);
        portalResponse.setTodaygramamount(String.valueOf(todayGrammarlyAmout));

        // 全部Gram订单数
        int totalGrammarlyCount = this.portalRepository.getTotalOrderCnt(param);
        portalResponse.setTotalgramcount(String.valueOf(totalGrammarlyCount));

        // 全部Gram订单金额
        int totalGrammarlyAmount = this.portalRepository.getTotalOrderAmount(param);
        portalResponse.setTotalgramamount(String.valueOf(totalGrammarlyAmount));

        param.put("checktype", "0");
        // 今日Turnin国际
        int todayTurnitinCnt = this.portalRepository.getTodayOrderCnt(param);
        portalResponse.setTodayturnincount(String.valueOf(todayTurnitinCnt));

        // 今日Turnin国际金额
        int todayTurnitinAmount = this.portalRepository.getTodayOrderAmount(param);
        portalResponse.setTodayturninamount(String.valueOf(todayTurnitinAmount));

        // 全部Turnin国际
        int totalTurnitinCnt = this.portalRepository.getTotalOrderCnt(param);
        portalResponse.setTotalturnincount(String.valueOf(totalTurnitinCnt));

        //  全部Turnin国际金额
        int totalTurnitinAmount = this.portalRepository.getTotalOrderAmount(param);
        portalResponse.setTotalturninamount(String.valueOf(totalTurnitinAmount));

        param.put("checktype", "1");
        // 今日TurninUK
        int todayTurnitinUKCnt = this.portalRepository.getTodayOrderCnt(param);
        portalResponse.setTodayturninukcount(String.valueOf(todayTurnitinUKCnt));

        // 今日TurninUK金额
        int todayTurnitinUKAmount = this.portalRepository.getTodayOrderAmount(param);
        portalResponse.setTodayturninukamount(String.valueOf(todayTurnitinUKAmount));

        // 全部TurninUK
        int totalTurnitinUKCnt = this.portalRepository.getTotalOrderCnt(param);
        portalResponse.setTotalturninukcount(String.valueOf(totalTurnitinUKCnt));

        // 全部TurninUK金额
        int totalTurnitinUKAmount = this.portalRepository.getTotalOrderAmount(param);
        portalResponse.setTotalturninukamount(String.valueOf(totalTurnitinUKAmount));

        log.info("首页统计信息返回的数据为：\n{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(responseData);
    }

    /**
     * 订单趋势数据查询.
     */
    public String lineService() {

        try {
            // 当天
            String today = DateTimeUtil.getCurrentDate();
            // 当天往前15天
            String startDay = DateTimeUtil.stepDays(today, -15);
            Map<String, String> param = new HashMap<>();
            param.put("startdate", startDay + "000000");
            param.put("enddate", today + "999999");

            // 查询
            List<Map<String, String>> lineData = this.portalRepository.getLineData(param);

            WebResponse<PortalResponse> responseData = new WebResponse<>();
            PortalResponse portalResponse = new PortalResponse();
            responseData.setResponse(portalResponse);
            portalResponse.setOrderlist(lineData);

            log.info("首页订单趋势查询返回的数据为：\n{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));

            // 返回
            return JSON.toJSONString(responseData);
        } catch (Exception ex) {
            log.error("获取订单趋势数据时异常：\n{}", ex.getMessage());
            return new SysErrResponse("获取订单趋势数据时异常").toJsonString();
        }
    }

    /**
     * 订单分布.
     */
    public String pieService() {

        List<Map<String, String>> statusData = this.portalRepository.getStatusData();
        List<Map<String, String>> checkTypeData = this.portalRepository.getCheckTypeData();

        WebResponse<PortalResponse> responseData = new WebResponse<>();
        PortalResponse portalResponse = new PortalResponse();
        responseData.setResponse(portalResponse);

        portalResponse.setStatussort(statusData);
        portalResponse.setChecktypesort(checkTypeData);

        log.info("首页订单分布查询返回的数据为：\n{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(responseData);
    }
}
