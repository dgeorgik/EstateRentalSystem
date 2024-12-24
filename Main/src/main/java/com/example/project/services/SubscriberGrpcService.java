package com.example.project.services;

import com.example.project.model.SubscriberGrpc;
import com.example.project.model.sub_class.SubscriptionResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 09.09.2024
 */

public interface SubscriberGrpcService {
    SubscriberGrpc getSubscriberGrpcById(Long id);
    List<SubscriberGrpc> getSubscriberGrpcByParams(String userEmail, String subCity, Long subPrice, Long subRooms);
    List<SubscriberGrpc> getAllSubscriberGrpc();
    ResponseEntity<SubscriptionResponse> createSubscriberGrpc(SubscriberGrpc subscriberGrpc);
    void deleteSubscriberGrpcById(Long id);

}
