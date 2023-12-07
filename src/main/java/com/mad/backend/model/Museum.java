package com.mad.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "museums")
public class Museum {
    @Id
    private String id;
    private String name;
    private String description;
    private String city;
    private String postalCode;
    private String image;
    private String imagePath;
    private String openingHours;
    private double price;

    public Museum() {
    }

    public Museum(String name, String description, String city, String postalCode, String image, String imagePath,
            String openingHours, double price) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.postalCode = postalCode;
        this.image = image;
        this.imagePath = imagePath;
        this.openingHours = openingHours;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Museum [id=" + id + ", name=" + name + ", description=" + description + ", city=" + city
                + ", postalCode=" + postalCode + ", image=" + image + ", imagePath=" + imagePath + ", openingHours="
                + openingHours + ", price=" + price + "]";
    }

}