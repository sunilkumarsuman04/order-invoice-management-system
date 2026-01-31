package com.invoicepayment.entity;


import lombok.Data;

@Data
public class Product {

    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer productQuantity;

    private String description;
}


