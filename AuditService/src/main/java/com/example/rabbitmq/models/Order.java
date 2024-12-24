package com.example.rabbitmq.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class Order {

    private int orderId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    public Order() {
    }

    public Order(int orderId, Date date) {
        this.orderId = orderId;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}