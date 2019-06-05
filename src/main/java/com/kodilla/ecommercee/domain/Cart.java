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
    @Column(name="ID", unique = true)
    private Long id;

    @Column(name="USER")
    private User user;

    @Column(name="LIST_OF_PRODUCTS")
    private List<Product> productsList = new ArrayList<>();

    public Cart(){

    }
    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Product> getProductsList(){
        return productsList;
    }
    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }



}







