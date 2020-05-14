package com.shop.project.entity;

import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

public class Product extends RepresentationModel<Product> {

    private long id;
    private String name;
    private BigDecimal price;
    private int amount;

    public Product() {
    }

    public Product(Long id, String name, BigDecimal price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
