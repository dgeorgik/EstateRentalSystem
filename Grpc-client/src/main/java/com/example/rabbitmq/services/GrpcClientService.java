package com.example.rabbitmq.services;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import realestate.BackendServiceGrpc;
import realestate.Realestate;

@Service
public class GrpcClientService {

    private static final Logger logger = LoggerFactory.getLogger(GrpcClientService.class);
    private final RabbitMqSender rabbitMQSender;

    public GrpcClientService(RabbitMqSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    public void processEvent(Realestate.Event event) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9091) 
                .build();

         BackendServiceGrpc.BackendServiceStub backendServiceStub = BackendServiceGrpc.newStub(channel);

         backendServiceStub.getMatchingSubscribers(event, new StreamObserver<Realestate.SubscriberResponse>() {
            @Override
            public void onNext(Realestate.SubscriberResponse response) {
                logger.info("Received response from gRPC backend: {}", response);
                rabbitMQSender.sendMessage(response);
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error while calling gRPC backend: {}", t.getMessage(), t);
            }

            @Override
            public void onCompleted() {
                logger.info("Completed gRPC backend request for event: {}", event.getId());
                channel.shutdown();
            }
        });
    }
}

