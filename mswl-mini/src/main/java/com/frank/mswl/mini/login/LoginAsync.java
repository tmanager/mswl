package com.frank.mswl.mini.login;

import com.frank.mswl.core.utils.CommonUtil;
import com.frank.mswl.core.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 小程序登录/注册异步任务.
 *
 * @author 张孝党 2020/01/06.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/06. 张孝党 创建.
 */
@Slf4j
@Component
public class LoginAsync {

    @Autowired
    private LoginRepository loginRepository;

    // 语法查重优惠券ID
    private String GRAMMARLY_ID = "31b6cf012f9911eaa6c300163e0bcfd2";

    // 查重优惠券ID
    private String TURNITIN_ID = "389e0e772f9911eaa6c300163e0bcfd2";

    /**
     * 赠送2个语法免费检测优惠券
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void addGrammarlyCoupon(String openId) {
        log.info("新用户赠送2个语法免费检测优惠券开始..............");

        try {
            for (int i = 0; i < 2; i++) {
                Map<String, String> param = new HashMap<>();
                // id
                param.put("id", CommonUtil.getUUid());
                // openid
                param.put("openid", openId);
                // 优惠券ID
                param.put("couponid", GRAMMARLY_ID);
                // 更新时间
                param.put("updtime", DateTimeUtil.getTimeformat());
                // 来源
                param.put("couponsource", "0");
                // 状态
                param.put("couponstatus", "0");
                int cnt = this.loginRepository.insWxUserCoupon(param);
                log.info("新增语法免费检测优惠券结果为：{}", cnt);

                // 等待1S
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            log.info(">>>>>>>>>>>>>\n{}", ex.getMessage());
        }

        log.info("新用户赠送2个语法免费检测优惠券结束..............");
    }

    /**
     * 赠送1个查重免费检测优惠券
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void addTurnitinCoupon(String openId) {
        log.info("完善信息后赠送1个查重免费检测优惠券开始..............");

        Map<String, String> param = new HashMap<>();
        // id
        param.put("id", CommonUtil.getUUid());
        // openid
        param.put("openid", openId);
        // 优惠券ID
        param.put("couponid", TURNITIN_ID);
        // 更新时间
        param.put("updtime", DateTimeUtil.getTimeformat());
        // 来源
        param.put("couponsource", "0");
        // 状态
        param.put("couponstatus", "0");
        int cnt = this.loginRepository.insWxUserCoupon(param);
        log.info("新增语法免费检测优惠券结果为：{}", cnt);

        log.info("完善信息后赠送1个查重免费检测优惠券结束..............");
    }
}