package com.example.rabbitmq;

import com.example.rabbitmq.models.AnnouncementGrpc;
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
public class SubscriberNotificationEventConsumer {

    private final AuditGrpcService auditGrpcService;

    @Autowired
    public SubscriberNotificationEventConsumer(AuditGrpcService auditGrpcService) {
        this.auditGrpcService = auditGrpcService;
    }

    @RabbitListener(queues = "audit-subscriber-notification-queue")
    public void consumeSubscriberNotification(AnnouncementGrpc announcementGrpc) {
        System.out.println("Предпочтение найдено! " + announcementGrpc.getRenovationGrpc());

        String ann = announcementGrpc.getCityGrpc() +" "+ announcementGrpc.getCountryGrpc()
                +" "+ announcementGrpc.getRenovationGrpc() +" "+ announcementGrpc.getArea() +" "
                + announcementGrpc.getPrice()+"";

        auditGrpcService.saveAuditRecord(
                "subscriber-notification-queue",
                "Уведомление об предпочтении " + ann,
                "",
                "",
                false,
                true
        );
    }
}
