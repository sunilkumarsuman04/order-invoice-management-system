package com.invoicepayment.dto.request;

import lombok.Data;

@Data
public class BuyProductRequestDTO {

    private Long productId;
    private Integer quantity;
}
