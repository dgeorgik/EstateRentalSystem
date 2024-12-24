package com.example.project.controller;

import com.example.project.model.SubscriberGrpc;
import com.example.project.model.sub_class.SubscriptionResponse;
import com.example.project.services.SubscriberGrpcService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 09.09.2024
 */

@RestController
@RequestMapping("/subscriber")
public class SubscriberGrpcController {
    @Autowired
    private SubscriberGrpcService subscriberGrpcService;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SubscriberGrpcController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @GetMapping("/{id}")
    public HttpEntity<SubscriberGrpc> getsubscriberGrpcById(@PathVariable Long id) {
        SubscriberGrpc subscriberGrpc = subscriberGrpcService.getSubscriberGrpcById(id);
        subscriberGrpc.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());

        return new ResponseEntity<>(subscriberGrpc, HttpStatus.OK);
    }

    @GetMapping
    public HttpEntity<List<SubscriberGrpc>> getAllSubscriber() {
        List<SubscriberGrpc> subscriberGrpcs = subscriberGrpcService.getAllSubscriberGrpc();

        subscriberGrpcs.forEach(subscriber ->
                subscriber.add(linkTo(methodOn(UserController.class).getUserById(subscriber.getId())).withSelfRel())
        );

        return new ResponseEntity<>(subscriberGrpcs, HttpStatus.OK);
    }



    @PostMapping("/search")
    public ResponseEntity<CollectionModel<EntityModel<SubscriberGrpc>>> getSubscribersByParams(@RequestBody SubscriberGrpc subscriberGrpc) {
        List<SubscriberGrpc> subscribers = subscriberGrpcService.getSubscriberGrpcByParams(
                subscriberGrpc.getUserEmail() != null ? subscriberGrpc.getUserEmail().getEmail() : null,
                subscriberGrpc.getSubCity(),
                subscriberGrpc.getSubPrice(),
                subscriberGrpc.getSubNumOfRooms()
        );

        List<EntityModel<SubscriberGrpc>> subscriberModels = subscribers.stream()
                .map(subscriber -> EntityModel.of(subscriber,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SubscriberGrpcController.class)
                                .getsubscriberGrpcById(subscriber.getId())).withSelfRel()))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<SubscriberGrpc>> collectionModel = CollectionModel.of(subscriberModels);
        collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SubscriberGrpcController.class)
                .getSubscribersByParams(subscriberGrpc)).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityModel<SubscriberGrpc>> createSubscriber(@RequestBody SubscriberGrpc subscriberGrpc) {
        ResponseEntity<SubscriptionResponse> response = subscriberGrpcService.createSubscriberGrpc(subscriberGrpc);

        SubscriptionResponse subscriptionResponse = response.getBody();

        if (subscriptionResponse != null && subscriptionResponse.getSubscription() != null) {
            SubscriberGrpc createdSubscriber = subscriptionResponse.getSubscription();
            EntityModel<SubscriberGrpc> subscriberModel = EntityModel.of(createdSubscriber);
//            rabbitTemplate.convertAndSend("grpc-created-exchange", "notification.new", announcementGrpc);
//            rabbitTemplate.convertAndSend("grpc-created-exchange", "audit.notification.new", announcementGrpc);

//            subscriberModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SubscriberGrpcController.class)
//                    .createSubscriber(subscriberGrpc)).withSelfRel());

            return ResponseEntity.status(HttpStatus.CREATED).body(subscriberModel);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSubscriber(@PathVariable Long id) {
        try {
            subscriberGrpcService.deleteSubscriberGrpcById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
