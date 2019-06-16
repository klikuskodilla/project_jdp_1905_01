package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CART")

public class Cart {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CART_ID", unique = true)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name ="ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Users user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_PRODUCT_CART",
            joinColumns = {@JoinColumn(name = "CART_ID",referencedColumnName = "CART_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ID",referencedColumnName = "ID")})
    private List<Product> productsList = new ArrayList<>();
    public Cart(){

    }
    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order){
        this.order = order;
    }

    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }

    public List<Product> getProductsList(){
        return productsList;
    }
    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }
}







