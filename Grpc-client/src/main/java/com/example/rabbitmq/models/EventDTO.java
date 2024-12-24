package com.example.rabbitmq.models;

/**
 * @author georgijpustovalov
 * @project Grpc-client
 * @Date 05.12.2024
 */
public class EventDTO {
    private int id;
    private String countryGrpc;
    private String cityGrpc;
    private String renovationGrpc;
    private int numberOfRooms;
    private int area;
    private int floor;
    private int price;
    private String message;
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
