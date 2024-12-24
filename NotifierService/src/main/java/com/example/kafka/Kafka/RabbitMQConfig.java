package com.example.kafka.Kafka;

/**
 * @author georgijpustovalov
 * @project NotifierService
 * @Date 25.10.2024
 */
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "subscriber-notification-queue";
    public static final String EXCHANGE_NAME = "grpc-created-exchange";
    public static final String ROUTING_KEY = "notification.new";

    @Bean
    public Queue subscriberNotificationQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange announcementExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingNotification(Queue subscriberNotificationQueue, TopicExchange announcementExchange) {
        return BindingBuilder.bind(subscriberNotificationQueue).to(announcementExchange).with(ROUTING_KEY);
    }
}