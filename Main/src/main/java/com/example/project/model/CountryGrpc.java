package com.example.project.model;


import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "country_grpc")
public class CountryGrpc extends RepresentationModel<CountryGrpc> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country")
    private String country;

    public CountryGrpc() {
    }

    public CountryGrpc(String country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
