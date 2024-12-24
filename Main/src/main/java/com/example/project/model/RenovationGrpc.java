package com.example.project.model;


import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "renovation_grpc")
public class RenovationGrpc extends RepresentationModel<RenovationGrpc> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "renovation")
    private String renovation;

    public RenovationGrpc() {
    }

    public RenovationGrpc(String renovation) {
        this.renovation = renovation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRenovation() {
        return renovation;
    }

    public void setRenovation(String renovation) {
        this.renovation = renovation;
    }
}
