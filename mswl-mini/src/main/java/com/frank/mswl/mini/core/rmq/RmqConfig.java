package com.frank.mswl.mini.core.rmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ配置信息.
 *
 * @author 张孝党 2019/12/23.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/12/23 张孝党 创建.
 */
@Slf4j
@Component
public class RmqConfig {

    /**
     * 注册exchange.
     */
    @Bean
    TopicExchange topicExchange() {
        log.info("注册exchange完成....");
        return new TopicExchange(RmqConst.QUEUE_NAME_GRAMMARLY_CLIENT);
    }
}
