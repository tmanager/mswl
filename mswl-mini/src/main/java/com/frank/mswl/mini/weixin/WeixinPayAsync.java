package com.frank.mswl.mini.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.frank.mswl.core.bean.RequestTurnitinBean;
import com.frank.mswl.core.bean.ResponseTurnitinBean;
import com.frank.mswl.core.bean.TurnitinConst;
import com.frank.mswl.core.redis.RedisService;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.DateTimeUtil;
import com.frank.mswl.mini.constant.CheckType;
import com.frank.mswl.mini.core.rmq.RmqService;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信支付完成后异步任务.
 *
 * @author 张孝党 2020.01/06.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020.01/06. 张孝党 创建.
 */

@Slf4j
@Component
public class WeixinPayAsync {

    @Autowired
    private RmqService rmqService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private WeixinPayRepository weixinPayRepository;

    /**
     * 根据商户订单号核销优惠券.
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void writeOffCouponByTradeNo(String tradeNo) {
        log.info("商户订单号[{}]核销优惠券开始.................", tradeNo);

        try {
            Map<String, String> param = new HashMap<>();
            param.put("tradeno", tradeNo);

            // 查询优惠券信息
            Map<String, String> result = this.weixinPayRepository.getCouponByTradeNo(param);
            log.info("查询出的优惠券信息为：[{}]", JSON.toJSONString(result, SerializerFeature.PrettyFormat));

            if (result == null || result.isEmpty()) {
                log.info("商户订单号[{}]没有使用优惠券!", tradeNo);
            } else {
                // 更新优惠券状态为1:已使用
                Map<String, String> param2 = new HashMap<>();
                param2.put("couponid", result.get("couponid"));
                param2.put("couponstatus", "1");
                param2.put("usetime", DateTimeUtil.getTimeformat());
                int cnt = this.weixinPayRepository.updCouponStatus(param2);
                log.info("优惠券[{}]更新为已使用,更新结果为:[{}]!", result.get("couponid"), cnt);
            }

        } catch (Exception ex) {
            log.info("商户订单号[{}]核销优惠券时异常,信息：\n{}", tradeNo, ex.getMessage());
        }

        log.info("商户订单号[{}]核销优惠券结束.................", tradeNo);
    }

    /**
     * 根据优惠券ID核销优惠券.
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void writeOffCouponByCouponId(String coupinId) {
        log.info("优惠券ID[{}]核销开始.................", coupinId);

        Map<String, String> param2 = new HashMap<>();
        param2.put("couponid", coupinId);
        param2.put("couponstatus", "1");
        param2.put("usetime", DateTimeUtil.getTimeformat());
        int cnt = this.weixinPayRepository.updCouponStatus(param2);
        log.info("优惠券[{}]更新为已使用,更新结果为:[{}]!", coupinId, cnt);

        log.info("优惠券ID[{}]核销结束.................", coupinId);
    }

    /**
     * 根据商户订单号增加用户积分.
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void writeOffMark(WxPayOrderNotifyResult notifyResult) {

        Map<String, String> param = new HashMap<>();
        // ID
        param.put("id", CommonUtil.getUUid());
        // openid
        param.put("openid", notifyResult.getOpenid());
        // 微信支付商户流水号
        param.put("tradeno", notifyResult.getOutTradeNo());
        // 支付金额,单位为分
        param.put("fee", String.valueOf(notifyResult.getTotalFee()));
        // 分录，0:增加积分,1:积分兑换,2:转增他人,3:他人赠与
        param.put("entry", "0");

        // 本次消费积分
        int mark = notifyResult.getTotalFee() / 100;
        log.info("增加的个人积分为:[{}]", mark);
        param.put("mark", String.valueOf(mark));

        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());
        int cnt = this.weixinPayRepository.insMarkHis(param);
        log.info("新增个人积分结果为：[{}]", cnt);
    }

    /**
     * 发起提交论文异步任务.
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void submitThesisByTradeNo(String tradeNo) throws Exception {

        // 查询订单信息
        Map<String, String> param = new HashMap<>();
        param.put("tradeno", tradeNo);
        List<Map<String, String>> orderInfoList = this.weixinPayRepository.getOrderInfoByTradeno(param);
        if (orderInfoList == null) {
            log.error(">>>>>>>>>>>>>>商户订单号[{}]未找到,请联系管理员!!!>>>>>>>>>>>>>>", tradeNo);
        }
        log.info("商户订单号[{}]的信息为：\n{}", tradeNo, JSON.toJSONString(orderInfoList, SerializerFeature.PrettyFormat));

        for (Map<String, String> orderInfo : orderInfoList) {
            // 判断提交的类型
            String checkType = orderInfo.get("checktype");
            if (checkType.equals(CheckType.TURNITIN.getValue())) {
                // 国际版
                this.submitInThesis(orderInfo);
            } else if (checkType.equals(CheckType.TURNITINUK.getValue())) {
                // UK版
                this.submitUKThesis(orderInfo);
            } else if (checkType.equals(CheckType.GRAMMARLY.getValue())) {
                // Grammarly
                this.submitGrammarlyThesis(
                        orderInfo.get("orderid"),
                        orderInfo.get("ext"),
                        orderInfo.get("originalurl"));
            } else {
                log.error("提交类型不符合,无法检测!!!");
            }
        }
    }

    /**
     * 发起提交论文异步任务.
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void submitThesisByOrderId(String[] orderIdList) throws Exception {

        // 查询订单信息
        for (String orderId : orderIdList) {
            Map<String, String> param = new HashMap<>();
            param.put("orderid", orderId);
            Map<String, String> orderInfo = this.weixinPayRepository.getOrderInfoByOrderId(param);
            if (orderInfo == null) {
                log.error(">>>>>>>>>>>>>>订单号[{}]未找到,请联系管理员!!!>>>>>>>>>>>>>>", orderId);
                return;
            }
            log.info("订单号[{}]的信息为：\n{}", orderId, JSON.toJSONString(orderInfo, SerializerFeature.PrettyFormat));

            // 判断提交的类型
            String checkType = orderInfo.get("checktype");
            if (checkType.equals(CheckType.TURNITIN.getValue())) {
                // 国际版
                this.submitInThesis(orderInfo);
            } else if (checkType.equals(CheckType.TURNITINUK.getValue())) {
                // UK版
                this.submitUKThesis(orderInfo);
            } else if (checkType.equals(CheckType.GRAMMARLY.getValue())) {
                // Grammarly
                this.submitGrammarlyThesis(
                        orderInfo.get("orderid"),
                        orderInfo.get("ext"),
                        orderInfo.get("originalurl"));
            } else {
                log.error("提交类型不符合,无法检测!!!");
            }
        }
    }


    /**
     * 提交国际版.
     */
    private void submitInThesis(Map<String, String> orderInfo) {
        log.info("提交国际版论文开始......................");

        try {
            RequestTurnitinBean turnBean = JSONObject.parseObject(this.redisService.getStringValue(TurnitinConst.TURN_IN_KEY),
                    RequestTurnitinBean.class);
            log.info("查询出的国际版账户信息为：\n{}", JSON.toJSONString(turnBean, SerializerFeature.PrettyFormat));

            // 保存论文信息
            String thesisName = orderInfo.get("orderid") + "." + orderInfo.get("ext");
            log.info("保存的论文名称为：[{}]", thesisName);
            //boolean dowloadResult = FileUtils.downloadFromHttpUrl(orderInfo.get("originalurl"), turnBean.getThesisVpnPath(), thesisName);
            //if (!dowloadResult) {
            //    log.error("从FDFS下载论文时异常");
            //    return;
            //}

            // 设置参数
            turnBean.setFirstName(orderInfo.get("firstname"));
            turnBean.setLastName(orderInfo.get("lastname"));
            turnBean.setTitle(orderInfo.get("titile"));
            turnBean.setThesisName(thesisName);
            turnBean.setOriginalurl(orderInfo.get("originalurl"));

            // 调用Socket
            //String result = SocketClient.callServer(TurnitinConst.SOCKET_SERVER, TurnitinConst.SOCKET_PORT,
            //        "02" + JSONObject.toJSONString(turnBean));
            String result = this.rmqService.rpcToTurnitin("02" + JSONObject.toJSONString(turnBean));
            ResponseTurnitinBean responseTurnitinBean = JSONObject.parseObject(result, ResponseTurnitinBean.class);
            log.info("调用Socket Server返回的结果为：\n{}", JSON.toJSONString(responseTurnitinBean, SerializerFeature.PrettyFormat));

            // 输出日志
            for (String logInfo : responseTurnitinBean.getLogList()) {
                log.info("turnitin-api>>>>>>" + logInfo);
            }
            // 提交失败
            if (!responseTurnitinBean.getRetcode().equals("0000")) {
                log.error("提交论文失败");
            }

            // 论文ID
            String thesisId = responseTurnitinBean.getThesisId();
            // 更新论文ID
            this.updThesisIdByOrderId(orderInfo.get("orderid"), thesisId);
        } catch (Exception ex) {
            log.error("提交国际版论文异常结束，异常信息为：\n{}", ex.getMessage());
        }
    }

    /**
     * 提交UK版.
     */
    private void submitUKThesis(Map<String, String> orderInfo) {
        log.info("提交UK版论文开始......................");

        try {
            RequestTurnitinBean turnBean = JSONObject.parseObject(this.redisService.getStringValue(TurnitinConst.TURN_UK_KEY),
                    RequestTurnitinBean.class);
            log.info("查询出的UK版账户信息为：\n{}", JSON.toJSONString(turnBean, SerializerFeature.PrettyFormat));

            // 保存论文信息
            String thesisName = orderInfo.get("orderid") + "." + orderInfo.get("ext");
            log.info("保存的论文名称为：[{}]", thesisName);
            //boolean dowloadResult = FileUtils.downloadFromHttpUrl(orderInfo.get("originalurl"), turnBean.getThesisVpnPath(), thesisName);
            //if (!dowloadResult) {
            //    log.error("从FDFS下载论文时异常");
            //    return;
            //}

            // 设计参数
            turnBean.setFirstName(orderInfo.get("firstname"));
            turnBean.setLastName(orderInfo.get("lastname"));
            turnBean.setTitle(orderInfo.get("titile"));
            turnBean.setThesisName(thesisName);
            turnBean.setOriginalurl(orderInfo.get("originalurl"));

            // 调用Socket
            //String result = SocketClient.callServer(TurnitinConst.SOCKET_SERVER, TurnitinConst.SOCKET_PORT,
            //        "12" + JSONObject.toJSONString(turnBean));
            String result = this.rmqService.rpcToTurnitin("12" + JSONObject.toJSONString(turnBean));
            ResponseTurnitinBean responseTurnitinBean = JSONObject.parseObject(result, ResponseTurnitinBean.class);
            log.info("调用Socket Server返回的结果为：\n{}", JSON.toJSONString(responseTurnitinBean, SerializerFeature.PrettyFormat));

            // 输出日志
            for (String logInfo : responseTurnitinBean.getLogList()) {
                log.info("turnitin-api>>>>>>" + logInfo);
            }
            // 提交失败
            if (!responseTurnitinBean.getRetcode().equals("0000")) {
                log.error("提交论文失败");
            }

            // 论文ID
            String thesisId = responseTurnitinBean.getThesisId();
            // 更新论文ID
            this.updThesisIdByOrderId(orderInfo.get("orderid"), thesisId);
        } catch (Exception ex) {
            log.error("提交UK版论文异常结束，异常信息为：\n{}", ex.getMessage());
        }
    }

    /**
     * 根据支付商户订单号更新论文ID.
     */
    private void updThesisIdByOrderId(String orderid, String thesisId) {

        Map<String, String> param = new HashMap<>();
        param.put("orderid", orderid);
        param.put("thesisid", thesisId);
        int cnt = this.weixinPayRepository.updThesisIdByOrderId(param);
        log.info("更新论文ID的结果为：[{}]", cnt);
    }

    /**
     * 提交Grammarly语法检测.
     */
    private void submitGrammarlyThesis(String orderId, String ext, String originalurl) {
        log.info("提交Grammarly论文开始......................");

        try {
            Map<String, String> param = new HashMap<>();
            param.put("jym", "jy_01");
            param.put("ddbh", orderId);
            param.put("ext", ext);
            param.put("file_url", originalurl);
            param.put("result_path", "/home/gramtu/tmp/grammarly/" + orderId + ".pdf");
            // 调用RabbitMQ
            String result = String.valueOf(this.rmqService.rpcToGrammarly(JSON.toJSONString(param)));
            log.info("发送Grammarly消息的结果为：[{}]", result);
            if (!result.equals("0000")) {
                // 记录失败的订单
                Map<String, String> param2 = new HashMap<>();
                param2.put("id", CommonUtil.getUUid());
                param2.put("orderid", orderId);
                param2.put("updtime", DateTimeUtil.getTimeformat());
                this.weixinPayRepository.insFailureOrder(param2);
            } else {
                // 删除失败订单
                Map<String, String> param3 = new HashMap<>();
                param3.put("orderid", orderId);
                this.weixinPayRepository.delFailureOrder(param3);
            }
        } catch (Exception ex) {
            log.error("提交Grammarly论文异常结束，异常信息为：\n{}", ex.getMessage());
        }
    }
}
