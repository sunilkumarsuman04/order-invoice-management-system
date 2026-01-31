package com.invoicepayment.dto.request;


import lombok.Data;

@Data
public class ProductRequestDTO {

    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer productQuantity;

    private String description;
}


