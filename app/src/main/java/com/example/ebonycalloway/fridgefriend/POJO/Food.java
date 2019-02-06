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
    private int healthGroup;
    private boolean shoppingList;
    private boolean fridgeList;

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amountLeft=" + amountLeft +
                ", price=" + price +
                ", expiration='" + expiration + '\'' +
                ", rating=" + rating +
                ", healthGroup=" + healthGroup +
                ", shoppingList=" + shoppingList +
                ", fridgeList=" + fridgeList +
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

    public int getHealthGroup() {
        return healthGroup;
    }

    public void setHealthGroup(int healthGroup) {
        this.healthGroup = healthGroup;
    }

    public boolean isShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(boolean shoppingList) {
        this.shoppingList = shoppingList;
    }

    public boolean isFridgeList() {
        return fridgeList;
    }

    public void setFridgeList(boolean fridgeList) {
        this.fridgeList = fridgeList;
    }
}
