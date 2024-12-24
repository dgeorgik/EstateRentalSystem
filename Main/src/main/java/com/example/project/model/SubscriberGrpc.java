package com.example.project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subscriber_grpc")
public class SubscriberGrpc extends RepresentationModel<SubscriberGrpc> implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", referencedColumnName = "email", nullable = true)
    @JsonIgnore
    private User userEmail;

    @Column(name = "sub_city")
    private String subCity;

    @Column(name = "sub_price")
    private Long subPrice;

    @Column(name = "sub_num_of_rooms")
    private Long subNumOfRooms;

    public SubscriberGrpc() {
    }


    public SubscriberGrpc(Long id, User userEmail, String subCity, Long subPrice, Long subNumOfRooms) {
        this.id = id;
        this.userEmail = userEmail;
        this.subCity = subCity;
        this.subPrice = subPrice;
        this.subNumOfRooms = subNumOfRooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(User userEmail) {
        this.userEmail = userEmail;
    }

    public String getSubCity() {
        return subCity;
    }

    public void setSubCity(String subCity) {
        this.subCity = subCity;
    }

    public Long getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(Long subPrice) {
        this.subPrice = subPrice;
    }

    public Long getSubNumOfRooms() {
        return subNumOfRooms;
    }

    public void setSubNumOfRooms(Long subNumOfRooms) {
        this.subNumOfRooms = subNumOfRooms;
    }


}
