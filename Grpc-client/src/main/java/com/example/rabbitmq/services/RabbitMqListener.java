package com.example.rabbitmq.services;

import com.example.rabbitmq.models.EventDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import realestate.Realestate;

/**
 * @author georgijpustovalov
 * @project Grpc-client
 * @Date 04.12.2024
 */
@Service
public class RabbitMqListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);
    private final GrpcClientService grpcClientService;
    private final ObjectMapper objectMapper;

    public RabbitMqListener(GrpcClientService grpcClientService, ObjectMapper objectMapper) {
        this.grpcClientService = grpcClientService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "new-announcement-queue")
    public void receiveMessage(String message) {
        try {
        logger.info("Received message from RabbitMQ: {}", message);

        EventDTO eventDTO = objectMapper.readValue(message, EventDTO.class);

        Realestate.Event eventRequest = Realestate.Event.newBuilder()
                .setId(eventDTO.getId())
                .setCountry(eventDTO.getCountryGrpc())
                .setCity(eventDTO.getCityGrpc())
                .setRenovation(eventDTO.getRenovationGrpc())
                .setNumberOfRooms(eventDTO.getNumberOfRooms())
                .setArea(eventDTO.getArea())
                .setFloor(eventDTO.getFloor())
                .setPrice(eventDTO.getPrice())
                .setMessage(eventDTO.getMessage())
                .build();

         grpcClientService.processEvent(eventRequest);

    } catch (Exception e) {
            logger.error("Error processing RabbitMQ message: {}", e.getMessage(), e);
        }

    }
//
//    private void processMessage(String message) {
//         logger.info("Processing message: {}", message);
//
//    }
}