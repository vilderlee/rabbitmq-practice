package com.vilderlee.deadletter.config;

import lombok.Data;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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

@Data @Configuration @ConfigurationProperties(prefix = "spring.rabbitmq") @PropertySource(value = "classpath:rabbitmq.properties") public class RabbitMQConfig {

    private String host;
    private int port;
    private String username;
    private String password;
    private String virtualHost;

    @Bean public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        //开启confirm机制（确保消息能够到达Exchange）
        factory.setPublisherConfirms(true);
        //开启return机制（确保消息能够路由到Queue）
        factory.setPublisherReturns(true);
        return factory;
    }

    @Bean public RabbitAdmin rabbitAdmin(CachingConnectionFactory cachingConnectionFactory) {
        return new RabbitAdmin(cachingConnectionFactory);
    }

    @Bean public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setCorrelationKey("VilderLee");
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            System.out.println("correlationData---->" + correlationData);
            System.out.println("ack---->" + ack);
            System.out.println("cause---->" + cause);
        });

        rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText,
                String exchange, String routingKey)->{
            System.out.println("message---->" + new String(message.getBody()));
            System.out.println("replyCode---->" + replyCode);
            System.out.println("replyText---->" + replyText);
            System.out.println("exchange---->" + exchange);
            System.out.println("routingKey---->" + routingKey);
        });

        return rabbitTemplate;
    }

}
