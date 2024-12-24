package com.example.rabbitmq.services;

import com.example.rabbitmq.models.AnnouncementSubscriberResult;
import com.example.rabbitmq.repositories.AnnouncementSubscriberRepository;
import com.google.protobuf.Empty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import io.grpc.stub.StreamObserver;
import realestate.BackendServiceGrpc;
import realestate.Realestate;


import java.util.List;
import java.util.UUID;

@Service
public class GrpcBackendService extends BackendServiceGrpc.BackendServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(GrpcBackendService.class);

    private final AnnouncementSubscriberRepository repository;

    public GrpcBackendService(AnnouncementSubscriberRepository repository) {
        this.repository = repository;
    }


    @Override
    public void getMatchingSubscribers(Realestate.EventRequest request, StreamObserver<Realestate.SubscriberResponse> responseObserver) {
        logger.info("Received EventRequest: {}", request);

        try {
            List<AnnouncementSubscriberResult> results = repository.findMatchingSubscribers(
                    request.getCity(),
                    request.getPrice(),
                    request.getNumberOfRooms()
            );

            System.out.println("Total results: " + results.size());

            results.forEach(result -> {
                System.out.println("Processing user: " + result.getUserEmail());

                System.out.println("Processing user: " + result.getLink());

                Realestate.SubscriberResponse response = Realestate.SubscriberResponse.newBuilder()
                        .setId(result.getId())
                        .setPrice(result.getPrice())
                        .setNumberOfRooms(result.getNumberOfRooms())
                        .setUserEmail(result.getUserEmail() != null ? result.getUserEmail() : "")
                        .setSubCity(result.getSubCity() != null ? result.getSubCity() : "")
                        .setSubPrice(result.getSubPrice() != null ? result.getSubPrice() : 0)
                        .setSubNumOfRooms(result.getSubNumOfRooms() != null ? result.getSubNumOfRooms() : 0)
                        .setLink(result.getLink() != null ? result.getLink() : "/")
                        .build();

                responseObserver.onNext(response);
            });

            responseObserver.onCompleted();
        } catch (Exception e) {
            logger.error("Error processing request", e);
            responseObserver.onError(e);
        }
    }

}
