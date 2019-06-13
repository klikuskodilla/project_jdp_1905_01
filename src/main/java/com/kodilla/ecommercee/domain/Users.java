package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "eMailAddress")
    private String eMailAddress;

    @NotNull
    @Column(name = "userAddress")
    private String userAddress;

    @Column(name = "account_start_date")
    private LocalDate accountStartDate;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "users",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> orders = new ArrayList<>();

    @OneToMany(
            targetEntity = Cart.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Cart> ordersInsideTheCart = new ArrayList<>();

    public Users() {
    }

    public Users(@NotNull Long id, @NotNull String name, @NotNull String surname, @NotNull String eMailAddress, @NotNull String userAddress, LocalDate accountStartDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.eMailAddress = eMailAddress;
        this.userAddress = userAddress;
        this.accountStartDate = accountStartDate;
    }

    public Users( @NotNull String name, @NotNull String surname, @NotNull String eMailAddress, @NotNull String userAddress, LocalDate accountStartDate) {
        this.name = name;
        this.surname = surname;
        this.eMailAddress = eMailAddress;
        this.userAddress = userAddress;
        this.accountStartDate = accountStartDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String geteMailAddress() {
        return eMailAddress;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public LocalDate getAccountStartDate() {
        return accountStartDate;
    }

    public List<Cart> getOrdersInsideTheCart(){
        return ordersInsideTheCart;
    }
    public void setOrdersInsideTheCart(Cart cart){
        ordersInsideTheCart.add(cart);
    }

}

