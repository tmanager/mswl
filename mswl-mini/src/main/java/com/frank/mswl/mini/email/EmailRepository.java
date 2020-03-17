package com.frank.mswl.mini.email;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 报告发送邮件Repository.
 *
 * @author 张孝党 2020/01/12.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/12. 张孝党 创建.
 */
@Mapper
@Repository
public interface EmailRepository {

    /**
     * 根据openId保存个人邮箱.
     */
    int updEmailByOpenId(Map<String, String> param);
}
