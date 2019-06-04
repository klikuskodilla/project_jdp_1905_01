package com.kodilla.ecommercee.domain;

public class Product {
//Mock class

    private Long id;

    private String name;

    private String details;

    private double price;

    public Product(String name, String details, double price) {
        this.name = name;
        this.details = details;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public double getPrice() {
        return price;
    }
}
