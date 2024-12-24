package com.example.rabbitmq.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import realestate.Realestate;

/**
 * @author georgijpustovalov
 * @project Grpc-client
 * @Date 04.12.2024
 */
@Service
public class RabbitMqSender {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqSender.class);
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;


    public RabbitMqSender(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Value("${spring.rabbitmq.template.exchange:}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key:}")
    private String routingKey;

    @Value("${rabbitmq.responseQueue}")
    private String responseQueue;

    public void sendMessage(Realestate.SubscriberResponse response) {

        try {
             String message = objectMapper.writeValueAsString(response);
             rabbitTemplate.convertAndSend(exchange, routingKey, message);
            logger.info("Sent message to RabbitMQ Exchange '{}', RoutingKey '{}': {}", exchange, routingKey, message);
        } catch (Exception e) {
            logger.error("Error sending message to RabbitMQ Exchange '{}', RoutingKey '{}': {}", exchange, routingKey, e.getMessage(), e);
        }
    }
}

