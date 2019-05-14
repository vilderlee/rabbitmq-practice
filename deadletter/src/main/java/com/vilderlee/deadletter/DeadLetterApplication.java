package com.vilderlee.deadletter;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableRabbit
public class DeadLetterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeadLetterApplication.class, args);
    }

}
