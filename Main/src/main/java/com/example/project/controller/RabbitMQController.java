package com.example.project.controller;

import com.example.project.model.AnnouncementGrpc;
import com.example.project.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author georgijpustovalov
 * @project RabbitMQ
 * @Date 16.10.2024
 */
@RestController
@RequestMapping("/api/rabbitmq")
public class RabbitMQController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


     @PostMapping("/new-announcement")
    public ResponseEntity<String> sendNewAnnouncement(@RequestBody AnnouncementGrpc announcementGrpc) {
        rabbitTemplate.convertAndSend("grpc-created-exchange", "announcement.new", announcementGrpc);

         rabbitTemplate.convertAndSend("grpc-created-exchange", "audit.announcement.new", announcementGrpc);
         return ResponseEntity.ok("Message sent to new-announcement-queue");
    }

     @PostMapping("/subscriber-notification")
    public ResponseEntity<String> sendSubscriberNotification(@RequestBody AnnouncementGrpc announcementGrpc) {
        rabbitTemplate.convertAndSend("grpc-created-exchange", "notification.new", announcementGrpc);
         rabbitTemplate.convertAndSend("grpc-created-exchange", "audit.notification.new", announcementGrpc);
         return ResponseEntity.ok("Message sent to subscriber-notification-queue");
    }

    @PostMapping("/price-update")
    public ResponseEntity<String> sendPriceUpdate(@RequestBody AnnouncementGrpc announcementGrpc) {
        rabbitTemplate.convertAndSend("grpc-created-exchange", "price.update", announcementGrpc);
        rabbitTemplate.convertAndSend("grpc-created-exchange", "audit.price.update", announcementGrpc);
        return ResponseEntity.ok("Message sent to price-update-queue");
    }

     @PostMapping("/subscriber-registration")
    public ResponseEntity<String> sendSubscriberRegistration(@RequestBody User user) {
        rabbitTemplate.convertAndSend("grpc-created-exchange", "subscriber.registration", user);
         rabbitTemplate.convertAndSend("grpc-created-exchange", "audit.subscriber.registration", user);
         return ResponseEntity.ok("Message sent to subscriber-registration-queue");
    }

}