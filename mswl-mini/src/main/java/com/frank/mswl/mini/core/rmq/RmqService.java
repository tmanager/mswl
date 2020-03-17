package com.frank.mswl.mini.core.rmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RabbitMQ服务类.
 *
 * @author 张孝党 2019/12/23.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/12/23 张孝党 创建.
 */
@Slf4j
@Service
public class RmqService {

    // 注册rabbitmq
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送exchange消息.
     */
    public String rpcToGrammarly(String msg) {
        log.info("*****************发送给grammarly的消息为：[{}]*****************", msg);

        // 发送消息
        Object result = this.rabbitTemplate.convertSendAndReceive(RmqConst.QUEUE_NAME_GRAMMARLY_CLIENT, msg);
        String response = new String((byte[]) result);
        log.info("*****************发送给grammarly的返回消息为：\n[{}]*****************", response);

        // 返回
        return response;
    }

    /**
     * 发送exchange消息.
     */
    public String rpcToTurnitin(String msg) {
        log.info("*****************发送给turnitin的消息为：\n[{}]*****************", msg);

        // 发送消息
        Object response = this.rabbitTemplate.convertSendAndReceive(
                RmqConst.QUEUE_NAME_TURNITIN_CLIENT,
                RmqConst.TOPIC_ROUTING_KEY,
                msg);
        log.info("*****************发送给turnitin的返回消息为：[{}]*****************", response);

        return response.toString();
    }
}
