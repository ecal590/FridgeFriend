package com.example.ebonycalloway.fridgefriend.POJO;

import java.util.Date;

/*
    Created by ebonycalloway on 8/2/17.
 */

public class Food {
    private String name;
    private String description;
    private double amountLeft;
    private double price;
    private String expiration;
    private double rating;
    private String healthGroup;

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amountLeft=" + amountLeft +
                ", price=" + price +
                ", expiration=" + expiration +
                ", rating=" + rating +
                ", healthGroup='" + healthGroup + '\'' +
                '}';
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

    public double getAmountLeft() {
        return amountLeft;
    }

    public void setAmountLeft(double amountLeft) {
        this.amountLeft = amountLeft;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getHealthGroup() {
        return healthGroup;
    }

    public void setHealthGroup(String healthGroup) {
        this.healthGroup = healthGroup;
    }
}