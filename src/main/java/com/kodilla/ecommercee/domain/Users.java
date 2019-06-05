package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Users {
//Mock users class

    private Long id;

    private String name;

    private String surname;

    private String eMailAddress;

    private String userAddress;

    private LocalDate accountStartDate;

    public Users() {
    }

    public Users(Long id, String name, String surname, String eMailAddress, String userAddress, LocalDate accountStartDate) {
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
}

