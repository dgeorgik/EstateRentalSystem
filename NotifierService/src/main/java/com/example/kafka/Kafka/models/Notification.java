package com.example.kafka.Kafka.models;

/**
 * @author georgijpustovalov
 * @project NotifierService
 * @Date 25.10.2024
 */
public class Notification {


    private int id;
    private int price;
    private int numberOfRooms;
    private int marketPrice;

    private String userEmail;
    private String subCity;
    private int subPrice;
    private int subNumOfRooms;
    private String link;


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(int marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSubCity() {
        return subCity;
    }

    public void setSubCity(String subCity) {
        this.subCity = subCity;
    }

    public int getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(int subPrice) {
        this.subPrice = subPrice;
    }

    public int getSubNumOfRooms() {
        return subNumOfRooms;
    }

    public void setSubNumOfRooms(int subNumOfRooms) {
        this.subNumOfRooms = subNumOfRooms;
    }
}
