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
public class PriceUpdateEventConsumer {


    private final AuditGrpcService auditGrpcService;

    @Autowired
    public PriceUpdateEventConsumer(AuditGrpcService auditGrpcService) {
        this.auditGrpcService = auditGrpcService;
    }

    @RabbitListener(queues = "audit-price-update-queue")
    public void consumePriceUpdate(AnnouncementGrpc announcementGrpc) {
        System.out.println("Получено обновление цены: " + announcementGrpc.getPrice() + " у объявления " + announcementGrpc.getId());

        auditGrpcService.saveAuditRecord(
                "price-update-queue",
                "Получено обновление цены: " + announcementGrpc.getPrice() + " у объявления " + announcementGrpc.getId(),
                "",
                "",
                false,
                true
        );
    }
}
