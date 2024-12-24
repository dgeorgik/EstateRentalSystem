package com.example.rabbitmq;

import com.example.rabbitmq.models.User;
import com.example.rabbitmq.services.AuditGrpcService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author georgijpustovalov
 * @project Kafka
 * @Date 16.10.2024
 */
@Component
public class SubscriberRegistrationEventConsumer {


    private final AuditGrpcService auditGrpcService;

    @Autowired
    public SubscriberRegistrationEventConsumer(AuditGrpcService auditGrpcService) {
        this.auditGrpcService = auditGrpcService;
    }

    @RabbitListener(queues = "audit-subscriber-registration-queue")
    public void consumeSubscriberRegistration(User user) {
        System.out.println("Регистрация подписчика: " + user.getName() + " " + user.getEmail());

        auditGrpcService.saveAuditRecord(
                "subscriber-registration-queue",
                "User registration",
                user.getName(),
                user.getEmail(),
                false,
                true
        );
    }
}
