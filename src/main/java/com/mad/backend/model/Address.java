package com.mad.backend.model;

import org.springframework.data.annotation.Id;


public class Address {
    @Id private String id;
    private String city;
    private String district;
    private String postalCode;
    public Address() {
    }
    public Address(String id, String city, String district, String postalCode) {
        this.id = id;
        this.city = city;
        this.district = district;
        this.postalCode = postalCode;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
}