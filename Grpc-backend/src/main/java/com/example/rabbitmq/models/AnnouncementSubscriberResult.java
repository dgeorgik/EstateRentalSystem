package com.example.rabbitmq.models;

import javax.persistence.*;

/**
 * @author georgijpustovalov
 * @project Grpc-backend
 * @Date 05.12.2024
 */

@Entity
@Table(name = "subscriber_announcement_view")
public class AnnouncementSubscriberResult {

    @Id
    private Long uniqueId;
    private Long id;
    private Integer price;
    private Integer numberOfRooms;
    private Integer marketPrice;
    private String userEmail;
    private String subCity;
    private Integer subPrice;
    private Integer subNumOfRooms;
    private String link;

    public AnnouncementSubscriberResult() {
    }


    public AnnouncementSubscriberResult(Long uniqueId, Long id, Integer price, Integer numberOfRooms, Integer marketPrice, String userEmail, String subCity, Integer subPrice, Integer subNumOfRooms, String link) {
        this.uniqueId = uniqueId;
        this.id = id;
        this.price = price;
        this.numberOfRooms = numberOfRooms;
        this.marketPrice = marketPrice;
        this.userEmail = userEmail;
        this.subCity = subCity;
        this.subPrice = subPrice;
        this.subNumOfRooms = subNumOfRooms;
        this.link = link;
    }

    public Long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
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

    public Integer getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(Integer subPrice) {
        this.subPrice = subPrice;
    }

    public Integer getSubNumOfRooms() {
        return subNumOfRooms;
    }

    public void setSubNumOfRooms(Integer subNumOfRooms) {
        this.subNumOfRooms = subNumOfRooms;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}