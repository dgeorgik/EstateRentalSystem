package com.example.rabbitmq.models;

/**
 * @author georgijpustovalov
 * @project demo
 * @Date 08.09.2024
 */


 import java.io.Serializable;

 public class User implements Serializable {

    private Long id;


    private String name;


    private String email;



    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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


 }
