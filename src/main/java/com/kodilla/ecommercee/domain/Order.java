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
    private Long orderId;

    @NotNull
    @Column(name = "order_price")
    private Double orderPrice;

    @NotNull
    @Column(name = "products_quantity")
    private Integer productsQuantity;

    @Column(name = "dateOfOrder")
    private LocalDate dateOfOrder;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_PRODUCT_ORDER",
            joinColumns = {@JoinColumn(name = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID")}
    )
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    public Order() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }

  public Users getUsers() {
        return users;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setProductsQuantity(Integer productsQuantity) {
        this.productsQuantity = productsQuantity;
    }

    public void setDateOfOrder(LocalDate dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}