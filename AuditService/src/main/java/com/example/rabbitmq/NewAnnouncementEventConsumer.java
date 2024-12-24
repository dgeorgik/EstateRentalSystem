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
public class NewAnnouncementEventConsumer {


    private final AuditGrpcService auditGrpcService;

    @Autowired
    public NewAnnouncementEventConsumer(AuditGrpcService auditGrpcService) {
        this.auditGrpcService = auditGrpcService;
    }

    @RabbitListener(queues = "audit-announcement-queue")
    public void consumeNewAnnouncement(AnnouncementGrpc announcementGrpc) {
        System.out.println("Получено новое объявление: " + announcementGrpc.getCountryGrpc() +", "+ announcementGrpc.getCityGrpc());

        String ann = announcementGrpc.getCityGrpc() +" "+ announcementGrpc.getCountryGrpc()
                +" "+ announcementGrpc.getRenovationGrpc() +" "+ announcementGrpc.getArea() +" "
                + announcementGrpc.getPrice()+"";
        auditGrpcService.saveAuditRecord(
                "new-announcement-queue",
                "Публикация нового уведомления" + ann,
                "",
                "",
                false,
                true
        );
    }
}

