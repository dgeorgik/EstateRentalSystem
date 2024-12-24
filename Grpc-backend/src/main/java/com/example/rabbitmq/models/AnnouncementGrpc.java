package com.example.rabbitmq.models;



public class AnnouncementGrpc {


    private Long id;


    private String countryGrpc;

    private String cityGrpc;

    private String renovationGrpc;

     private Long numberOfRooms;

     private Long area;

     private Long floor;

     private Long price;

     private String message;

    public AnnouncementGrpc() {
    }

    public AnnouncementGrpc(Long id, String countryGrpc, String cityGrpc, String renovationGrpc, Long numberOfRooms, Long area, Long floor, Long price, String message) {
        this.id = id;
        this.countryGrpc = countryGrpc;
        this.cityGrpc = cityGrpc;
        this.renovationGrpc = renovationGrpc;
        this.numberOfRooms = numberOfRooms;
        this.area = area;
        this.floor = floor;
        this.price = price;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryGrpc() {
        return countryGrpc;
    }

    public void setCountryGrpc(String countryGrpc) {
        this.countryGrpc = countryGrpc;
    }

    public String getCityGrpc() {
        return cityGrpc;
    }

    public void setCityGrpc(String cityGrpc) {
        this.cityGrpc = cityGrpc;
    }

    public String getRenovationGrpc() {
        return renovationGrpc;
    }

    public void setRenovationGrpc(String renovationGrpc) {
        this.renovationGrpc = renovationGrpc;
    }

    public Long getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
