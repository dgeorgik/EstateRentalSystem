package com.example.project.model;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;


@Entity
@Table(name = "announcement_grpc")
public class AnnouncementGrpc  extends RepresentationModel<AnnouncementGrpc> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_country_grpc", referencedColumnName = "id", nullable = true)
    private CountryGrpc countryGrpc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_grpc", referencedColumnName = "id", nullable = true)
    private CityGrpc cityGrpc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_renovation_grpc", referencedColumnName = "id", nullable = true)
    private RenovationGrpc renovationGrpc;

    @Column(name = "number_of_rooms")
    private Long numberOfRooms;

    @Column(name = "area")
    private Long area;

    @Column(name = "floor")
    private Long floor;

    @Column(name = "price")
    private Long price;

    public AnnouncementGrpc() {
    }

    public AnnouncementGrpc(CountryGrpc countryGrpc, CityGrpc cityGrpc, RenovationGrpc renovationGrpc, Long numberOfRooms, Long area, Long floor, Long price) {
        this.countryGrpc = countryGrpc;
        this.cityGrpc = cityGrpc;
        this.renovationGrpc = renovationGrpc;
        this.numberOfRooms = numberOfRooms;
        this.area = area;
        this.floor = floor;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CountryGrpc getCountryGrpc() {
        return countryGrpc;
    }

    public void setCountryGrpc(CountryGrpc countryGrpc) {
        this.countryGrpc = countryGrpc;
    }

    public CityGrpc getCityGrpc() {
        return cityGrpc;
    }

    public void setCityGrpc(CityGrpc cityGrpc) {
        this.cityGrpc = cityGrpc;
    }

    public RenovationGrpc getRenovationGrpc() {
        return renovationGrpc;
    }

    public void setRenovationGrpc(RenovationGrpc renovationGrpc) {
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
}
