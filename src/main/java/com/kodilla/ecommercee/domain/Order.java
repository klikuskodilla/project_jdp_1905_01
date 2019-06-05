package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER")
public class Order {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long order_id;

    @NotNull
    @Column(name = "order_price")
    private Double order_price;

    @NotNull
    @Column(name = "products_quantity")
    private Integer order_quantity;

    @Column(name = "dateOfOrder")
    private LocalDate dateOfOrder;

    @NotNull
    @Column(name = "products")
    private List<Product> products = new ArrayList<>();

    @NotNull
    @Column(name = "users")
    private List<Users> users = new ArrayList<>();

    public Order() {
    }

    public Long getOrder_id() {
        return order_id;
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

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
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
