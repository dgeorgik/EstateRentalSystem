package com.example.project.model;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */


import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users_grpc")
public class User extends RepresentationModel<User> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "userEmail",  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SubscriberGrpc> subscriptions;

    public User() {}

    public User(Long id, String name, String email, List<SubscriberGrpc> subscriptions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subscriptions = subscriptions;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SubscriberGrpc> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriberGrpc> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
