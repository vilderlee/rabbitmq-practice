package com.vilderlee.deadletter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) @SpringBootTest public class DeadletterApplicationTests {

    @Test public void contextLoads() {
    }

    @Autowired
    private RabbitTemplate template;

    @Test public void testSend(){

        Message message = new Message("Hello world!".getBytes(),new MessageProperties());
        template.send("amq.direct", "direct", message);
    }
}
