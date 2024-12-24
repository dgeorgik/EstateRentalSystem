package com.example.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация RabbitMQ для использования Jackson2JsonMessageConverter.
 */
@Configuration
public class RabbitMQConfig {


    @Value("${rabbitmq.queue.new_announcement}")
    private String newAnnouncementQueue;

    @Value("${rabbitmq.queue.subscriber_notification}")
    private String subscriberNotificationQueue;

    @Value("${rabbitmq.queue.price_update}")
    private String priceUpdateQueue;

    @Value("${rabbitmq.queue.subscriber_registration}")
    private String subscriberRegistrationQueue;

    @Bean
    public Queue newAnnouncementQueue() {
        return new Queue(newAnnouncementQueue, true);
    }

    @Bean
    public Queue subscriberNotificationQueue() {
        return new Queue(subscriberNotificationQueue, true);
    }

    @Bean
    public Queue priceUpdateQueue() {
        return new Queue(priceUpdateQueue, true);
    }

    @Bean
    public Queue subscriberRegistrationQueue() {
        return new Queue(subscriberRegistrationQueue, true);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
