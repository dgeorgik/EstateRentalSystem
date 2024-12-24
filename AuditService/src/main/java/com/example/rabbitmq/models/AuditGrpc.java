package com.example.rabbitmq.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "audit_grpc")
public class AuditGrpc {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_create")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create;

    @Column(name = "rabbit_queue")
    private String rabbitQueue;

    @Column(name = "message")
    private String message;

    @Column(name = "publishing")
    private Boolean publishing;

    @Column(name = "subscriber")
    private Boolean subscriber;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreate() {
        return this.create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public String getRabbitQueue() {
        return this.rabbitQueue;
    }

    public void setRabbitQueue(String rabbitQueue) {
        this.rabbitQueue = rabbitQueue;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getPublishing() {
        return this.publishing;
    }

    public void setPublishing(Boolean publishing) {
        this.publishing = publishing;
    }

    public Boolean getSubscriber() {
        return this.subscriber;
    }

    public void setSubscriber(Boolean subscriber) {
        this.subscriber = subscriber;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
