package com.frank.mswl.web.portal;

import com.frank.mswl.core.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PortalResponse extends BaseResponse {

    // 今日注册用户
    private String todayuser = "0";

    // 总用户数
    private String totaluser = "0";

    // 完善信息用户数
    private String peruser = "0";

    // 今日订单数
    private String todayordercount = "0";

    // 今日订单金额
    private String todayorderamount = "0";

    // 总订单数
    private String totalordercount = "0";

    // 总金额
    private String totalorderamount = "0";

    // 今日Grammarly订单数
    private String todaygramcount = "0";

    // 今日Grammarly订单金额
    private String todaygramamount = "0";

    // 全部Grammarly订单数
    private String totalgramcount = "0";

    // 全部Grammarly订单金额
    private String totalgramamount = "0";

    // 今日Turnin国际
    private String todayturnincount = "0";

    // 今日Turnin国际金额
    private String todayturninamount = "0";

    // 全部Turnin国际
    private String totalturnincount = "0";

    //  全部Turnin国际金额
    private String totalturninamount = "0";

    // 今日TurninUK
    private String todayturninukcount = "0";

    // 今日TurninUK金额
    private String todayturninukamount = "0";

    // 全部TurninUK
    private String totalturninukcount = "0";

    // 全部TurninUK金额
    private String totalturninukamount = "0";

    // 订单趋势
    private List<Map<String, String>> orderlist;

    private List<Map<String, String>> statussort;

    private List<Map<String, String>> checktypesort;
}
