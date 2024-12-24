package com.example.kafka.Kafka.services;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.SocketIOClient;
import com.example.kafka.Kafka.RabbitMQConfig;
import com.example.kafka.Kafka.models.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final Sinks.Many<Notification> sink = Sinks.many().multicast().onBackpressureBuffer();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SocketIOServer socketIOServer;

    private final ConcurrentHashMap<String, SocketIOClient> emailToClientMap = new ConcurrentHashMap<>();

    public NotificationService(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
        initializeListeners();
    }

    private void initializeListeners() {
         socketIOServer.addConnectListener(client -> {
            String email = client.getHandshakeData().getSingleUrlParam("email");
            if (email != null && !email.isEmpty()) {
                emailToClientMap.put(email, client);
                client.sendEvent("registered", "Successfully registered with email: " + email);
                logger.info("Client connected and registered with email: {}", email);
            } else {
                logger.warn("Client did not provide email. Disconnecting...");
                client.disconnect();
            }
        });


        socketIOServer.addDisconnectListener(client -> {
            emailToClientMap.values().remove(client);
            logger.info("Client disconnected");
        });
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        try {
            logger.info("Received message from RabbitMQ: {}", message);
            Notification notification = objectMapper.readValue(message, Notification.class);
            sink.tryEmitNext(notification);

            String userEmail = notification.getUserEmail();
            String link = notification.getLink();
            SocketIOClient client = emailToClientMap.get(userEmail);

            if (client != null) {
                logger.info("Found client for email: {}", userEmail);
//                client.sendEvent("userNotification", notification);
//                client.sendEvent("userNotification", message);
                Map<String, String> notificationMessage = new HashMap<>();
                notificationMessage.put("message", notification.getSubCity());
                notificationMessage.put("links", notification.getLink());
                client.sendEvent("userNotification", notificationMessage);

                logger.info("Sent notification to client: {}", userEmail);
                logger.info("Sent notification to client: {}", link);
                logger.info("userNotification " + notification);
                logger.info("Sent notification to client: {}", userEmail);
            } else {
                logger.warn("No client found for email: {}. Current registered emails: {}",
                        userEmail, emailToClientMap.keySet());
            }
        } catch (Exception e) {
            logger.error("Failed to process message", e);
        }
    }

    public Flux<Notification> getMessages() {
        return sink.asFlux();
    }
}
