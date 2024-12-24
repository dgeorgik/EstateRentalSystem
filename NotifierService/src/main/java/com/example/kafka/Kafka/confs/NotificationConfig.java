package com.example.kafka.Kafka.confs;

/**
 * @author georgijpustovalov
 * @project NotifierService
 * @Date 05.12.2024
 */
import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class NotificationConfig {

    @Bean
    public ConcurrentHashMap<String, SocketIOClient> emailToClientMap() {
        return new ConcurrentHashMap<>();
    }
}