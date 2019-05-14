package com.vilderlee.deadletter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/5/14      Create this file
 * </pre>
 */

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "direct.queue")
    public void getMessage(Message message){
        System.out.println("11111111111111111111111110" + new String(message.getBody()));
    }
}
