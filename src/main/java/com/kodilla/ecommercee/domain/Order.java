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
    @Column(name = "order_name")
    private String order_name;

    @NotNull
    @Column(name = "order_price")
    private Double order_price;

    @NotNull
    @Column(name = "order_quantity")
    private Integer order_quantity;

    @Column(name = "dateOfOrder")
    private LocalDate dateOfOrder;

    @NotNull
    @Column(name = "products")
    private List<Product> products = new ArrayList<>();


    public Order() {
    }

    public Order(String order_name, Double order_price, Integer order_quantity, LocalDate dateOfOrder, List<Product> products) {
        this.order_name = order_name;
        this.order_price = products.get(order_quantity).getPrice();
        this.order_quantity = products.size();
        this.dateOfOrder = dateOfOrder;
        this.products = products;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public String getOrder_name() {
        return order_name;
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
}
