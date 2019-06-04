package com.kodilla.ecommercee.domain.Dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {
    private Long order_id;
    private String order_name;
    private Double order_price;
    private Integer order_quantity;
    private LocalDate dateOfOrder;

    public OrderDto(Long order_id, String order_name, Double order_price, Integer order_quantity, LocalDate dateOfOrder) {
        this.order_id = order_id;
        this.order_name = order_name;
        this.order_price = order_price;
        this.order_quantity = order_quantity;
        this.dateOfOrder = dateOfOrder;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public Double getOrder_price() {
        return order_price;
    }

    public Integer getOrder_quantity() {
        return order_quantity;
    }

    public LocalDate getDateOfOrder() {
        return dateOfOrder;
    }
}
