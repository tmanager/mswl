package com.frank.mswl.mini.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.frank.mswl.core.response.SysErrResponse;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.IdGeneratorUtils;
import com.frank.mswl.mini.config.WxPayConfigBean;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.github.binarywang.wxpay.util.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信支付Service.
 *
 * @author 张孝党 2020.01/01.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020.01/01. 张孝党 创建.
 */
@Slf4j
@Service
public class WeixinPayService implements InitializingBean {

    @Autowired
    private WeixinPayAsync weixinPayAsync;

    @Autowired
    private WeixinPayRepository weixinPayRepository;

    @Autowired
    private WxPayConfigBean wxPayConfigBean;

    private WxPayConfig wxPayConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.wxPayConfig = new WxPayConfig();
        this.wxPayConfig.setAppId(this.wxPayConfigBean.getAppID());
        this.wxPayConfig.setMchId(this.wxPayConfigBean.getMchId());
        this.wxPayConfig.setMchKey(this.wxPayConfigBean.getMchKey());
        this.wxPayConfig.setKeyPath(this.wxPayConfigBean.getKeyPath());
    }

    /**
     * 统一下单.
     */
    @Transactional(rollbackFor = Exception.class)
    public String unifiedOrderService(WeixinPayRequest requestData) throws Exception {

        // 支付金额
        DecimalFormat df = new DecimalFormat("#");
        String amount = String.valueOf(df.format(Double.valueOf(requestData.getAmount()) * 100));
        log.info("支付金额为：{}", amount);

        // 订单金额为0时直接发起异步检测
        if (amount.equals("0")) {
            // 修改原订单状态为2:已支付
            Map<String, String> param = new HashMap<>();
            param.put("orderid", requestData.getOrderIdList()[0]);
            param.put("status", "2");
            int cnt = this.weixinPayRepository.updOrderByOrderId(param);
            log.info("更新订单状态为已支付的结果为:[{}]", cnt);

            // 发起异步检测
            log.info("发起异步检测....................");
            this.weixinPayAsync.submitThesisByOrderId(requestData.getOrderIdList());

            // 优惠券核销
            log.info("发起异步优惠券核销................");
            this.weixinPayAsync.writeOffCouponByCouponId(requestData.getCoupId());

            // 返回,不支付金额时返回retcode:0001
            return new SysResponse("0001", "论文开始检测...........").toJsonString();
        } else {
            // 返回报文.
            Map<String, String> rspMap = new HashMap<>();

            // 请求参数
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            // 公众账号ID
            orderRequest.setAppid(this.wxPayConfigBean.getAppID());
            // 商户号
            orderRequest.setMchId(this.wxPayConfigBean.getMchId());
            // 随机字符串
            String nonceStr = CommonUtil.getUUid();
            orderRequest.setNonceStr(nonceStr);
            // 商品描述
            orderRequest.setBody("查重费用");

            // 商户订单号
            IdGeneratorUtils idGeneratorUtils = new IdGeneratorUtils(0, 0);
            String tradeNo = String.valueOf(idGeneratorUtils.nextId());
            log.info("微信支付下单商户订单号为：" + tradeNo);
            orderRequest.setOutTradeNo(tradeNo);

            // TODO：标价金额
            orderRequest.setTotalFee(Integer.valueOf(amount));
            //orderRequest.setTotalFee(Integer.valueOf("1"));

            // 终端IP
            String ip = "127.0.0.1";
            log.info("取得的终端IP为：" + ip);
            orderRequest.setSpbillCreateIp(ip);
            // 通知地址
            orderRequest.setNotifyUrl(this.wxPayConfigBean.getNotifyUrl());
            // 交易类型
            orderRequest.setTradeType("JSAPI");
            // 用户标识
            orderRequest.setOpenid(requestData.getOpenId());

            // 调用服务
            WxPayService wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(this.wxPayConfig);
            // 后台请求
            WxPayUnifiedOrderResult orderResult = wxPayService.unifiedOrder(orderRequest);
            log.info("统一下载返回的信息为：\n{}", JSON.toJSONString(orderRequest, SerializerFeature.PrettyFormat));

            if (orderResult.getReturnCode().equals("SUCCESS") && orderResult.getResultCode().equals("SUCCESS")) {
                // 下单成功
                log.info("微信支付统一下单成功");

                // 返回值
                WebResponse<WeixinPayResponse> responseData = new WebResponse<>();
                WeixinPayResponse weixinPayResponse = new WeixinPayResponse();
                weixinPayResponse.setNoncestr(nonceStr);
                // 获取时间戳除以千变字符串
                Long s = System.currentTimeMillis() / 1000;
                String timeStamp = String.valueOf(s);
                weixinPayResponse.setTimestamp(timeStamp);
                weixinPayResponse.setPaypackage("prepay_id=" + orderResult.getPrepayId());

                // 二次签名
                Map<String, String> map = new HashMap<>();
                map.put("appId", this.wxPayConfigBean.getAppID());
                map.put("timeStamp", timeStamp);
                map.put("nonceStr", nonceStr);
                map.put("package", "prepay_id=" + orderResult.getPrepayId());
                map.put("signType", "MD5");
                String sign = SignUtils.createSign(map, "MD5", this.wxPayConfigBean.getMchKey(), null);
                weixinPayResponse.setPaysign(sign);

                // 根据订单号更新支付流程中的商户订单号,订单状态修改为1:待支付
                this.updOrderByOrderId(requestData.getOrderIdList(), requestData.getCoupId(), tradeNo);

                responseData.setResponse(weixinPayResponse);
                log.info("返回信息为：\n{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));

                // 返回
                return JSON.toJSONString(responseData);
            } else {
                // 下单失败
                log.info("微信支付统一下单失败");
                return new SysErrResponse("微信支付统一下单失败!").toJsonString();
            }
        }
    }

    /**
     * 微信支付异步通知.
     */
    @Transactional(rollbackFor = Exception.class)
    public void callbackNotifyService(String xmlData) {

        try {
            WxPayService wxPayService = new WxPayServiceImpl();
            wxPayService.setConfig(this.wxPayConfig);

            // 解析返回数据
            WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);
            log.info("解析的微信支付异步通知数据为：\n{}", JSON.toJSONString(notifyResult, SerializerFeature.PrettyFormat));

            // 新增一条支付订单
            Map<String, String> param = new HashMap<>();
            param.put("id", CommonUtil.getUUid());
            // 商户订单号
            param.put("tradeno", notifyResult.getOutTradeNo());
            // 微信支付订单号
            param.put("transactionid", notifyResult.getTransactionId());
            // 交易类型
            param.put("tradetype", notifyResult.getTradeType());
            // 付款银行
            param.put("banktype", notifyResult.getBankType());
            // 货币种类
            param.put("feetype", notifyResult.getFeeType());
            // openid
            param.put("openid", notifyResult.getOpenid());
            // 支付完成时间
            param.put("timeend", notifyResult.getTimeEnd());
            // 订单金额
            param.put("totalfee", String.valueOf(notifyResult.getTotalFee()));
            // 状态为2
            param.put("status", "2");
            int cnt = this.weixinPayRepository.insJyLs(param);
            log.info("新增支付流水结果为：[{}]", cnt);

            // 更新订单表的中状态为检测中
            int cnt2 = this.weixinPayRepository.updOrderByTradeNo(param);
            log.info("更新原订单流水结果为：[{}]", cnt2);

            // 发起异步检测
            log.info("发起异步检测....................");
            this.weixinPayAsync.submitThesisByTradeNo(notifyResult.getOutTradeNo());

            // 增加积分
            log.info("发起异步增加积分..................");
            this.weixinPayAsync.writeOffMark(notifyResult);

            // 消费优惠券
            log.info("发起异步优惠券核销................");
            this.weixinPayAsync.writeOffCouponByTradeNo(notifyResult.getOutTradeNo());
        } catch (Exception ex) {
            log.info("微信支付异步通知异常：" + ex.getMessage());
        }
    }

    /**
     * 更新订单信息.
     */
    private void updOrderByOrderId(String[] orderList, String couponId, String tradeNo) {

        log.info("共有{}个订单需要更新", orderList.length);

        for (int i = 0; i < orderList.length; i++) {
            Map<String, String> param = new HashMap<>();
            param.put("orderid", orderList[i]);
            param.put("couponid", couponId);
            param.put("tradeno", tradeNo);
            // 待支付状态
            param.put("status", "1");
            this.weixinPayRepository.updOrderByOrderId(param);
        }
    }

    /**
     * 查询个人消费记录.
     */
    public String queryJyls(WeixinPayRequest requestData) {

        // openid
        String openId = requestData.getOpenId();

        Map<String, Object> param = new HashMap<>();
        param.put("openid", requestData.getOpenId());
        // 分页信息
        if (requestData.getPagesize() != 0) {
            param.put("startindex", requestData.getStartindex());
            param.put("pagesize", requestData.getPagesize());
            param.put("pagingOrNot", "1");
        }

        // 查询
        List<Map<String, String>> consumeList = this.weixinPayRepository.queryConsumeListByOpenId(param);
        log.info("查询出的个人消费记录为：\n{}", JSON.toJSONString(consumeList, SerializerFeature.PrettyFormat));

        WebResponse<WeixinPayResponse> responseData = new WebResponse<>();
        WeixinPayResponse weixinPayResponse = new WeixinPayResponse();
        responseData.setResponse(weixinPayResponse);
        // 消费记录
        weixinPayResponse.setConsumelist(consumeList);

        log.info("返回信息为：\n{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(responseData);
    }
}
