package com.frank.mswl.mini.coupon;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.frank.mswl.core.response.SysErrResponse;
import com.frank.mswl.core.response.SysResponse;
import com.frank.mswl.core.response.WebResponse;
import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.DateTimeUtil;
import com.frank.mswl.mini.constant.CheckType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上程序端优惠券Service.
 *
 * @author 张孝党 2020/01/06.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/06. 张孝党 创建.
 */
@Slf4j
@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    /**
     * 小程序端个人优惠券查询.
     */
    public String queryCouponService(CouponRequest requestData) {

        // 根据openid查询优惠券列表
        Map<String, Object> param = new HashMap<>();
        param.put("openid", requestData.getOpenId());
        // 语法检测
        if (requestData.getCheckType().equals(CheckType.GRAMMARLY.getValue())) {
            param.put("type", "1");
        } else {
            // 查重
            param.put("type", "0");
        }

        // 分页信息
        if (requestData.getPagesize() != 0) {
            param.put("startindex", requestData.getStartindex());
            param.put("pagesize", requestData.getPagesize());
            param.put("pagingOrNot", "1");
        }

        List<Map<String, String>> couponList = this.couponRepository.getCouponList(param);
        log.info("查询出的个人优惠券信息为：{}", couponList);
        int cnt = this.couponRepository.getCouponListCnt(param);

        WebResponse<CouponResponse> responseData = new WebResponse<>();
        CouponResponse couponResponse = new CouponResponse();
        responseData.setResponse(couponResponse);
        // 优惠券列表
        couponResponse.setCouponlist(couponList);
        couponResponse.setTotalcount(cnt);

        log.info("返回信息为：\n{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));

        // 返回
        return JSON.toJSONString(responseData);
    }

    /**
     * 优惠券转赠.
     */
    @Transactional(rollbackFor = Exception.class)
    public String givenService(CouponRequest requestData) {
        log.info("优惠券转赠开始.................");

        // 判断手机号是否存在
        Map<String, String> param = new HashMap<>();
        param.put("phonenumber", requestData.getPhoneNumber());
        Map<String, String> wxUserInfoMap = this.couponRepository.wxUserPhoneExists(param);
        if (wxUserInfoMap == null || wxUserInfoMap.size() == 0) {
            return new SysErrResponse("被转赠人手机号不存在！").toJsonString();
        }
        String newOpenId = wxUserInfoMap.get("openid");
        log.info("接收优惠券的人的openid为：[{}]", newOpenId);

        // 判断优惠券的状态
        param.put("id", requestData.getId());
        Map<String, String> couponStatusMap = this.couponRepository.getCouponInfo(param);
        if (!couponStatusMap.get("couponstatus").equals("0")) {
            return new SysErrResponse("正有正常状态的优惠券才能转赠！").toJsonString();
        }

        if(newOpenId.equals(requestData.getOpenId())) {
            return new SysErrResponse("不能转赠给自己！").toJsonString();
        }

        // 新增一条优惠券信息
        Map<String, String> paramAdd = new HashMap<>();
        paramAdd.put("id", CommonUtil.getUUid());
        paramAdd.put("openid", newOpenId);
        paramAdd.put("couponid", requestData.getCouponId());
        paramAdd.put("updtime", DateTimeUtil.getTimeformat());
        paramAdd.put("couponstatus", "0");
        paramAdd.put("couponsource", "3");
        paramAdd.put("givedopenid", requestData.getOpenId());
        int cnt = this.couponRepository.insNewCouponHis(paramAdd);
        log.info("新增优惠券使用记录的结果为：[{}]", cnt);

        // 更新原有优惠券状态
        Map<String, String> paramUpd = new HashMap<>();
        paramUpd.put("id", requestData.getId());
        paramUpd.put("givedopenid", newOpenId);
        paramUpd.put("givedphone", requestData.getPhoneNumber());
        paramUpd.put("couponstatus", "2");
        paramUpd.put("usetime", DateTimeUtil.getTimeformat());
        int cnt2 = this.couponRepository.updCouponHis(paramUpd);
        log.info("更新原优惠券使用记录的结果为：[{}]", cnt2);

        // 返回
        return new SysResponse("操作成功").toJsonString();
    }
}
