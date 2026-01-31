package com.invoicepayment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDTO {

    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer productQuantity;


    private String description;
}
