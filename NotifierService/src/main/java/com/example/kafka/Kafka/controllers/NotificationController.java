package com.example.kafka.Kafka.controllers;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.kafka.Kafka.models.Notification;
import com.example.kafka.Kafka.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.concurrent.ConcurrentHashMap;


/**
 * @author georgijpustovalov
 * @project Kafka
 * @Date 16.10.2024
 */

@RestController
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;

    private final SocketIOServer socketIOServer;
    private final ConcurrentHashMap<String, SocketIOClient> emailToClientMap;


    public NotificationController(NotificationService notificationService,
                                  SocketIOServer socketIOServer,
                                  ConcurrentHashMap<String, SocketIOClient> emailToClientMap) {
        this.notificationService = notificationService;
        this.socketIOServer = socketIOServer;
        this.emailToClientMap = emailToClientMap;
    }

    @GetMapping("/test/register")
    public String testRegister(@RequestParam String email) {
        if (emailToClientMap.containsKey(email)) {
            return "Client already registered with email: " + email;
        } else {
            logger.info("Testing registration for email: {}", email);
            return "No client registered with email: " + email;
        }
    }

    @GetMapping("/test/clients")
    public ConcurrentHashMap<String, SocketIOClient> getAllRegisteredClients() {
        logger.info("Returning all registered clients");
        return emailToClientMap;
    }

    @GetMapping(value = "/notifications", produces = "text/event-stream")
    public Flux<Notification> streamNotifications() {
        logger.info("Client connected to /notifications");
        return notificationService.getMessages()
                .doOnNext(notification -> logger.info("Sending notification to client: {}", notification))
                .doOnError(error -> logger.error("Error sending notification: ", error));

//        return notificationService.getMessages()
//                .doOnNext(notification -> logger.info("Sending notification to client: " + notification));
    }
}