package com.example.petcare;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String description;
    private double price;
    private int imageResourceId;

    public Product(String name, String description, double price, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getImageResource() {
        return imageResourceId;
    }
}
