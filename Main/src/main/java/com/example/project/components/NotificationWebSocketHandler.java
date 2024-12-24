package com.example.project.components;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationWebSocketHandler extends TextWebSocketHandler {

    private final ConcurrentHashMap<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String email = session.getUri().getQuery().split("=")[1];
        userSessions.put(email, session);
        session.sendMessage(new TextMessage("Connected to Main Service!"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(new TextMessage("Echo: " + message.getPayload()));
    }

    public void sendNotification(String email, String notification) {
        WebSocketSession session = userSessions.get(email);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(notification));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        userSessions.values().removeIf(existingSession -> existingSession.equals(session));
    }
}