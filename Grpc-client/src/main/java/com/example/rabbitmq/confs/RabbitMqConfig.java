package com.example.rabbitmq.confs;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author georgijpustovalov
 * @project Grpc-client
 * @Date 04.12.2024
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue eventsQueue() {
        return new Queue("new-announcement-queue", true);
    }
}