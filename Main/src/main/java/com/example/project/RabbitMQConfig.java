package com.example.project;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author georgijpustovalov
 * @project Kafka
 * @Date 16.10.2024
 */
@Configuration
public class RabbitMQConfig {


    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


    @Bean
    public Queue newRowAnnouncementQueue() {
        return new Queue("new-announcement-queue", true);
    }

    @Bean
    public Queue newRowSubscriberNotificationQueue() {
        return new Queue("subscriber-notification-queue", true);
    }

    @Bean
    public Queue newPriceUpdateQueue() {
        return new Queue("price-update-queue", true);
    }

    @Bean
    public Queue newRowSubscriberRegistrationQueue() {
        return new Queue("subscriber-registration-queue", true);
    }

    @Bean
    public TopicExchange rowCreatedExchange() {
        return new TopicExchange("grpc-created-exchange");
    }

    @Bean
    public Binding bindingNewAnnouncement(Queue newRowAnnouncementQueue, TopicExchange announcementExchange) {
        return BindingBuilder.bind(newRowAnnouncementQueue).to(announcementExchange).with("announcement.new");
    }

    @Bean
    public Binding bindingNotification(Queue newRowSubscriberNotificationQueue, TopicExchange announcementExchange) {
        return BindingBuilder.bind(newRowSubscriberNotificationQueue).to(announcementExchange).with("notification.new");
    }

    @Bean
    public Binding bindingPriceUpdate(Queue newPriceUpdateQueue, TopicExchange announcementExchange) {
        return BindingBuilder.bind(newPriceUpdateQueue).to(announcementExchange).with("price.update");
    }

    @Bean
    public Binding bindingSubscriberRegistration(Queue newRowSubscriberRegistrationQueue, TopicExchange announcementExchange) {
        return BindingBuilder.bind(newRowSubscriberRegistrationQueue).to(announcementExchange).with("subscriber.registration");
    }


    /**
     * AUDIT
     */


    @Bean
    public Queue auditRowAnnouncementQueue() {
        return new Queue("audit-announcement-queue", true);
    }

    @Bean
    public Queue auditRowSubscriberNotificationQueue() {
        return new Queue("audit-subscriber-notification-queue", true);
    }

    @Bean
    public Queue auditPriceUpdateQueue() {
        return new Queue("audit-price-update-queue", true);
    }

    @Bean
    public Queue auditRowSubscriberRegistrationQueue() {
        return new Queue("audit-subscriber-registration-queue", true);
    }

    @Bean
    public Binding bindingAuditAnnouncement(Queue auditRowAnnouncementQueue, TopicExchange announcementAuditExchange) {
        return BindingBuilder.bind(auditRowAnnouncementQueue).to(announcementAuditExchange).with("audit.announcement.new");
    }

    @Bean
    public Binding bindingAuditNotification(Queue auditRowSubscriberNotificationQueue, TopicExchange announcementExchange) {
        return BindingBuilder.bind(auditRowSubscriberNotificationQueue).to(announcementExchange).with("audit.notification.new");
    }

    @Bean
    public Binding bindingAuditPriceUpdate(Queue auditPriceUpdateQueue, TopicExchange announcementExchange) {
        return BindingBuilder.bind(auditPriceUpdateQueue).to(announcementExchange).with("audit.price.update");
    }

    @Bean
    public Binding bindingAuditSubscriberRegistration(Queue auditRowSubscriberRegistrationQueue, TopicExchange announcementExchange) {
        return BindingBuilder.bind(auditRowSubscriberRegistrationQueue).to(announcementExchange).with("audit.subscriber.registration");
    }
}