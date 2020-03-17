package com.frank.mswl.web.portal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页数据查询Controller.
 *
 * @author 张孝党 2020/01/18.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/18 张孝党 创建.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/portal")
public class PortalContronller {

    @Autowired
    private PortalService portalService;

    /**
     * 首页统计数据查询.
     */
    @RequestMapping("/query/statistics")
    public String statistics() {
        log.info("首页统计数据查询开始..................");

        // 查询数据
        String responseData = this.portalService.statisticsService();

        log.info("首页统计数据查询结束..................");
        log.info("首页统计数据查询返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 订单趋势数据查询.
     */
    @RequestMapping("/query/line")
    public String line() {
        log.info("首页订单趋势数据查询开始..................");

        // 查询数据
        String responseData = this.portalService.lineService();

        log.info("首页订单趋势数据查询结束..................");
        log.info("首页订单趋势数据查询返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 订单分布数据查询.
     */
    @RequestMapping("query/distribute")
    public String pie() {
        log.info("首页订单分布数据查询开始..................");

        // 查询数据
        String responseData = this.portalService.pieService();

        log.info("首页订单分布数据查询结束..................");
        log.info("首页订单分布数据查询返回值为:{}", responseData);
        return responseData;
    }
}
