package com.example.project.model;


import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "city_grpc")
public class CityGrpc extends RepresentationModel<CityGrpc> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    public CityGrpc() {
    }

    public CityGrpc(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
