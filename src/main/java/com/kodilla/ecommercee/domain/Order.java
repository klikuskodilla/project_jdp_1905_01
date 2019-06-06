package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "price")
    @NotNull
    private Double order_price;

    @Column(name = "quantity")
    @NotNull
    private Integer order_quantity;

    @Column(name = "dateOfOrder")
    private LocalDate dateOfOrder;

    private List<Product> products = new ArrayList<>();

    private List<Users> users = new ArrayList<>();

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public Double getOrder_price() {
        return products.get(order_quantity).getPrice();
    }

    public Integer getOrder_quantity() {
        return products.size();
    }

    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder_price(Double order_price) {
        this.order_price = order_price;
    }

    public void setOrder_quantity(Integer order_quantity) {
        this.order_quantity = order_quantity;
    }

    public void setDateOfOrder(LocalDate dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
