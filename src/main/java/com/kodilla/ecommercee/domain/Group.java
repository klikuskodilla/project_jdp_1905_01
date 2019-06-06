package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="GROUPS")
public class Group {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long groupId;

    @NotNull
    @Column(name="name")
    private String name;

    @OneToMany(targetEntity = Product.class, mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> productList = new ArrayList<>();

    public Group() {
    }

    public Group(@NotNull String name) {
        this.name = name;
    }

    public Long getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
