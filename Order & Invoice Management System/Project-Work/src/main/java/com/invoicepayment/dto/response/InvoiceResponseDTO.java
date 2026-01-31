package com.invoicepayment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "invoice_id",
        "product_id",
        "product_name",
        "product_price",
        "total_price",
        "discount_amount",
        "net_amount",
        "description"
})
public class InvoiceResponseDTO {

    @JsonProperty("invoice_id")
    private Long invoiceId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_price")
    private Double productPrice;

    @JsonProperty("items")
    private List<OrderItemResponseDTO> items;

    @JsonProperty("total_price")
    private Double totalPrice;

    @JsonProperty("discount_amount")
    private Double discountAmount;

    @JsonProperty("net_amount")
    private Double netAmount;

    @JsonProperty("description")
    private String description;
}
