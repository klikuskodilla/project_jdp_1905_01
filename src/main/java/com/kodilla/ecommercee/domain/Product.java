package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="PRODUCTS")
public class Product {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="details")
    private String details;

    @Column(name="price")
    @NotNull
    private double price;
  
    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();
  
    @ManyToOne
    @JoinColumn(name="GROUP_ID")
    private Group group;
    public Product() {
    }

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

    public Group getGroup() {
        return group;
    }
}