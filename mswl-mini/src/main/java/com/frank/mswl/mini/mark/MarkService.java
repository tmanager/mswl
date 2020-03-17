package com.frank.mswl.mini.mark;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.frank.mswl.core.response.SysErrResponse;
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
 * 上程序端积分信息Service.
 *
 * @author 张孝党 2020/01/08.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/08. 张孝党 创建.
 */
@Slf4j
@Service
public class MarkService {

    @Autowired
    private MarkRepository markRepository;

    /**
     * 个人积分列表查询.
     */
    public String queryService(MarkRequest requestData) {
        log.info("个人积分列表查询开始...............");

        String mark = "0";

        // 查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("openid", requestData.getOpenId());

        // 分页信息
        if (requestData.getPagesize() != 0) {
            param.put("startindex", requestData.getStartindex());
            param.put("pagesize", requestData.getPagesize());
            param.put("pagingOrNot", "1");
        }

        // 获取积分信息
        Map<String, String> markMap = this.markRepository.getMarkByOpenId(param);
        log.info("获取到的个人积分信息为：[{}]", JSON.toJSONString(markMap, SerializerFeature.PrettyFormat));
        if (markMap == null || markMap.isEmpty()) {
            mark = "0";
        } else {
            mark = String.valueOf(markMap.get("mark"));
        }

        // 获取个人积分列表
        List<Map<String, String>> markList = this.markRepository.getMarkListByOpenId(param);
        log.info("查询出的个人积分列表为：{}", markList);

        WebResponse<MarkResponse> responseData = new WebResponse<>();
        MarkResponse markResponse = new MarkResponse();
        responseData.setResponse(markResponse);
        // 积分
        markResponse.setMark(mark);
        // 积分列表
        markResponse.setMarklist(markList);

        // 返回
        log.info("返回的个人积分信息为：\n{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));
        return JSON.toJSONString(responseData);
    }

    /**
     * 积分转赠.
     */
    @Transactional(rollbackFor = Exception.class)
    public String givenService(MarkRequest requestData) {
        log.info("积分转赠开始.................");

        // 判断手机号是否存在
        Map<String, String> param = new HashMap<>();
        param.put("phonenumber", requestData.getGivenPhone());
        Map<String, String> wxUserInfoMap = this.markRepository.wxUserPhoneExists(param);
        if (wxUserInfoMap == null || wxUserInfoMap.size() == 0) {
            return new SysErrResponse("被转赠人手机号不存在！").toJsonString();
        }
        String newOpenId = wxUserInfoMap.get("openid");
        log.info("接收积分人的openid为：[{}]", newOpenId);

        if (newOpenId.equals(requestData.getOpenId())) {
            return new SysErrResponse("不能转赠给自己！").toJsonString();
        }

        // 新增一条积分信息：接收人增加积分
        Map<String, String> paramAdd = new HashMap<>();
        paramAdd.put("id", CommonUtil.getUUid());
        paramAdd.put("openid", newOpenId);
        // 3:他人赠与
        paramAdd.put("entry", "3");
        paramAdd.put("updtime", DateTimeUtil.getTimeformat());
        paramAdd.put("mark", requestData.getGivenMark());
        paramAdd.put("givedopenid", "");
        paramAdd.put("givedphone", "");
        int cnt = this.markRepository.insNewMarkHis(paramAdd);
        log.info("新增积分使用记录的结果为：[{}]", cnt);

        // 新增一条积分信息：转赠减少积分
        Map<String, String> paramAdd2 = new HashMap<>();
        paramAdd2.put("id", CommonUtil.getUUid());
        paramAdd2.put("openid", requestData.getOpenId());
        // 2:转赠他人
        paramAdd2.put("entry", "2");
        paramAdd2.put("updtime", DateTimeUtil.getTimeformat());
        paramAdd2.put("mark", requestData.getGivenMark());
        paramAdd2.put("givedopenid", newOpenId);
        paramAdd2.put("givedphone", requestData.getGivenPhone());
        int cnt2 = this.markRepository.insNewMarkHis(paramAdd2);
        log.info("新增积分使用记录的结果为：[{}]", cnt2);

        // 返回
        return new SysResponse("操作成功").toJsonString();
    }

    /**
     * 查询可用优惠券.
     */
    public String couponQueryService(MarkRequest requestData) {

        Map<String, String> param = new HashMap<>();
        param.put("currentdate", DateTimeUtil.getCurrentDate());
        List<Map<String, String>> couponList = this.markRepository.getCanUseConponList(param);
        log.info("查询出的可用优惠券信息为：\n{}", JSON.toJSONString(
                couponList,
                SerializerFeature.PrettyFormat
        ));

        WebResponse<MarkResponse> responseData = new WebResponse<>();
        MarkResponse markResponse = new MarkResponse();
        responseData.setResponse(markResponse);
        // 优惠券列表
        markResponse.setCouponlist(couponList);
        markResponse.setTotalcount(couponList.size());

        // 返回
        log.info("返回的可用优惠券信息为：\n{}", JSON.toJSONString(responseData, SerializerFeature.PrettyFormat));
        return JSON.toJSONString(responseData);
    }

    /**
     * 兑换优惠券.
     */
    @Transactional(rollbackFor = Exception.class)
    public String couponExchangeService(MarkRequest requestData) {

        // 查询参数
        Map<String, String> param = new HashMap<>();
        param.put("id", requestData.getCoupId());
        // 查询
        Map<String, String> couponInfo = this.markRepository.getCouponInfo(param);
        log.info("查询出的优惠券信息为：\n{}", JSON.toJSONString(couponInfo, SerializerFeature.PrettyFormat));

        // 判断优惠券的个数
        if (couponInfo.get("numbers").equals(couponInfo.get("usenumbers"))) {
            return new SysErrResponse("该优惠券已无库存!").toJsonString();
        }

        // 判断优惠券的状态
        if (!couponInfo.get("status").equals("0")) {
            return new SysErrResponse("该优惠券已下架,请刷新!").toJsonString();
        }

        // 修改优惠券个数及状态
        int useNumbers = Integer.valueOf(couponInfo.get("usenumbers")) + 1;
        if (useNumbers == Integer.valueOf(couponInfo.get("numbers"))) {
            param.put("status", "2");
        }
        param.put("usenumbers", String.valueOf(useNumbers));
        int cnt = this.markRepository.updCouponInfo(param);
        log.info("修改优惠券信息的结果为：[{}]", cnt);

        // 新增个人优惠券
        param.put("id", CommonUtil.getUUid());
        param.put("openid", requestData.getOpenId());
        param.put("couponid", requestData.getCoupId());
        param.put("updtime", DateTimeUtil.getTimeformat());
        param.put("usemark", couponInfo.get("usemark"));
        int cnt2 = this.markRepository.insNewCouponHis(param);
        log.info("新增个人优惠券的结果为：[{}]", cnt2);

        // 新增个人积分
        Map<String, String> paramAdd = new HashMap<>();
        paramAdd.put("id", CommonUtil.getUUid());
        paramAdd.put("openid", requestData.getOpenId());
        // 1:积分总的
        paramAdd.put("entry", "1");
        paramAdd.put("updtime", DateTimeUtil.getTimeformat());
        paramAdd.put("mark", couponInfo.get("usemark"));
        paramAdd.put("givedopenid", "");
        paramAdd.put("givedphone", "");
        int cnt3 = this.markRepository.insNewMarkHis(paramAdd);
        log.info("新增积分使用记录的结果为：[{}]", cnt3);

        return new SysResponse("兑换成功....").toJsonString();
    }
}
