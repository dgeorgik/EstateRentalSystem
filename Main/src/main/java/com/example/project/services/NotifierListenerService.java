package com.example.project.services;

import com.example.project.components.NotificationWebSocketHandler;
import com.example.project.model.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.PostConstruct;

@Service
public class NotifierListenerService {

    private final NotificationWebSocketHandler mainWebSocketHandler;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NotifierListenerService(NotificationWebSocketHandler mainWebSocketHandler) {
        this.mainWebSocketHandler = mainWebSocketHandler;
    }

    @PostConstruct
    public void connectToNotifier() {
        StandardWebSocketClient client = new StandardWebSocketClient();

        client.doHandshake(new TextWebSocketHandler() {
            @Override
            protected void handleTextMessage(org.springframework.web.socket.WebSocketSession session, org.springframework.web.socket.TextMessage message) throws Exception {
                String notificationJson = message.getPayload();
                Notification notification = objectMapper.readValue(notificationJson, Notification.class);
                mainWebSocketHandler.sendNotification(notification.getUserEmail(), notificationJson);
                System.out.println("[handleTextMessage] + " + notification.getSubCity());
            }
        }, "ws://localhost:9092/notifications");
    }
}
