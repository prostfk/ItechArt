package by.itechart.contacts.model.entity;

import lombok.Data;

@Data
public class Address {

    private Long id;
    private String country;
    private String city;
    private String street;
    private Integer house;
    private Integer flat;
    private Integer postIndex;//str

    public Address() {
    }

    public Address(String country, String city, String street, Integer house, Integer flat, Integer postIndex) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.postIndex = postIndex;
    }

    public Address(Long id, String country, String city, String street, Integer house, Integer flat, Integer postIndex) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.postIndex = postIndex;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    public Integer getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(Integer postIndex) {
        this.postIndex = postIndex;
    }
}
