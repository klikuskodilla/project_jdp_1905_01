package com.kodilla.ecommercee.domain.Dto;

public class ProductDto {

    private Long id;
    private String name;
    private String details;
    private double price;

    public ProductDto(Long id, String name, String details, double price) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.price = price;
    }

    public ProductDto() {
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }
}
