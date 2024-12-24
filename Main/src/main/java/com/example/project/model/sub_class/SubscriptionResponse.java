package com.example.project.model.sub_class;

import com.example.project.model.SubscriberGrpc;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 22.09.2024
 */
public class SubscriptionResponse {
    private String message;
    private SubscriberGrpc subscription;

    public SubscriptionResponse(String message, SubscriberGrpc subscription) {
        this.message = message;
        this.subscription = subscription;
    }

    // Геттеры и сеттеры
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SubscriberGrpc getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriberGrpc subscription) {
        this.subscription = subscription;
    }
}

