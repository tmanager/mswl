package com.frank.mswl.web.coupon;

import com.alibaba.fastjson.JSON;
import com.frank.mswl.core.request.WebRequest;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 优惠券Service.
 *
 * @author 张孝党 2020/01/07.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/07 张孝党 创建.
 */
@Slf4j
@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    /**
     * 查询优惠券一览.
     */
    public String queryService(CouponRequest requestData) {

        String couponname = requestData.getCouponname();
        String type = requestData.getType();

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("couponname", couponname);
        param.put("type", type);
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");

        // 查询结果
        List<Map<String, String>> dataList = this.couponRepository.getCouponList(param);
        log.info("查询出的结果为：{}", dataList);
        // 条数
        int cnt = this.couponRepository.getCnt(param);
        log.info("查询出的数据条数为：{}", cnt);

        WebResponse<CouponReponse> responseData = new WebResponse<>();
        CouponReponse couponReponse = new CouponReponse();
        couponReponse.setDraw(0);
        couponReponse.setTotalcount(cnt);
        couponReponse.setCouplist(dataList);
        responseData.setResponse(couponReponse);

        return JSON.toJSONString(responseData);
    }

    /**
     * 新增优惠券.
     */
    @Transactional(rollbackFor = Exception.class)
    public String couponAddService(WebRequest<CouponRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // id主键
        param.put("id", CommonUtil.getUUid());
        // 优惠券名称
        param.put("couponname",requestData.getRequest().getCouponname());
        // 优惠券描述
        param.put("couponbak",requestData.getRequest().getCouponbak());
        // 需要兑换的积分
        param.put("usemark",requestData.getRequest().getUsemark());
        // 满多少元可用
        param.put("upfee",requestData.getRequest().getUpfee());
        // 发行个数
        param.put("numbers",requestData.getRequest().getNumbers());
        // 有效结束日期
        String endDate = requestData.getRequest().getEnddate().replace("-","");
        if (StringUtils.isEmpty(endDate))
        {
            endDate="99999999";
        }
        param.put("enddate",endDate);
        // 分类
        param.put("type",requestData.getRequest().getType());
        // 抵用多少钱
        param.put("amount",requestData.getRequest().getAmount());
        // 状态
        param.put("status",requestData.getRequest().getStatus());
        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());
        // 更新人
        param.put("upduid", requestData.getUserid());

        // 新增
        int cnt = this.couponRepository.addCoupon(param);
        log.info("新增数据条数为：[{}]", cnt);

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 根据优惠券ID获取优惠券详细内容.
     */
    public String couponDetailService(CouponRequest requestData) {

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("couponid", requestData.getId());
        param.put("phone", requestData.getPhone());
        param.put("startindex", requestData.getStartindex());
        param.put("pagesize", requestData.getPagesize());
        param.put("pagingOrNot", "1");

        // 获取优惠券详细内容
        List<Map<String, String>> dataList = this.couponRepository.getCouponDetail(param);

        log.info("查询出的结果为：{}", dataList);
        // 条数
        int cnt = this.couponRepository.getHisCnt(param);
        log.info("查询出的数据条数为：{}", cnt);

        WebResponse<CouponReponse> responseData = new WebResponse<>();
        CouponReponse couponReponse = new CouponReponse();
        couponReponse.setDraw(0);
        couponReponse.setTotalcount(cnt);
        couponReponse.setCouphislist(dataList);
        responseData.setResponse(couponReponse);

        // 返回
        return JSON.toJSONString(responseData);
    }

    /**
     * 删除优惠券.
     */
    @Transactional(rollbackFor = Exception.class)
    public String delCouponService(String[] couponIdList) {

        for (String couponId :couponIdList) {
            Map<String, String> param = new HashMap<>();
            param.put("id", couponId);
            this.couponRepository.deleteCoupon(param);
            log.info("优惠券[{}]被删除", couponId);
        }

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 编辑保存优惠券.
     */
    @Transactional(rollbackFor = Exception.class)
    public String editCouponService(WebRequest<CouponRequest> requestData) {

        // 参数
        Map<String, String> param = new HashMap<>();
        // ID
        param.put("id", requestData.getRequest().getId());
        // 优惠券名称
        param.put("couponname",requestData.getRequest().getCouponname());
        // 优惠券描述
        param.put("couponbak",requestData.getRequest().getCouponbak());
        // 需要兑换的积分
        param.put("usemark",requestData.getRequest().getUsemark());
        // 满多少元可用
        param.put("upfee",requestData.getRequest().getUpfee());
        // 发行个数
        param.put("numbers",requestData.getRequest().getNumbers());
        // 有效结束日期
        String endDate = requestData.getRequest().getEnddate().replace("-","");
        if (StringUtils.isEmpty(endDate))
        {
            endDate="99999999";
        }
        param.put("enddate",endDate);
        // 分类
        param.put("type",requestData.getRequest().getType());
        // 抵用多少钱
        param.put("amount",requestData.getRequest().getAmount());
        // 状态
        param.put("status",requestData.getRequest().getStatus());
        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());
        // 更新人
        param.put("upduid", requestData.getUserid());
        this.couponRepository.updCoupon(param);

        // 返回
        return new SysResponse().toJsonString();
    }

    /**
     * 新增优惠券赠送新增.
     */
    @Transactional(rollbackFor = Exception.class)
    public String couponHisAddService(WebRequest<CouponRequest> requestData) {

        //获取微信用户openid
        String openId = this.couponRepository.getOpenid(requestData.getRequest().getPhone());
        if (StringUtils.isEmpty(openId))
        {
            // 返回
            return new SysResponse("0001","受赠者不存在，请重新注册！").toJsonString();
        }

        //获取已使用个数
        int chkUseNumbers = this.couponRepository.chkUseNumbers(requestData.getRequest().getId());
        if (chkUseNumbers == 0)
        {
            // 所赠送的优惠券的状态不能进行赠送
            return new SysResponse("0002","所选优惠券不能进行赠送！").toJsonString();
        }

        // 参数
        Map<String, String> param = new HashMap<>();
        // id主键
        param.put("id", CommonUtil.getUUid());
        // 优惠券id
        param.put("couponid",requestData.getRequest().getId());
        // 转赠人手机号
        param.put("openid",openId);
        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());

        // 新增
        int cnt = this.couponRepository.addCouponHis(param);
        log.info("新增数据条数为：[{}]", cnt);

        //使用个数更新
        cnt = this.couponRepository.updUseNumbers(requestData.getRequest().getId());
        log.info("使用个数数据条数为：[{}]", cnt);

        // 返回
        return new SysResponse().toJsonString();
    }

}
