package com.invoicepayment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentResponse {

    @JsonProperty("payment_mode")
    private String paymentMode;

    @JsonProperty("entered_amount")
    private double enteredAmount;

    @JsonProperty("service_charge")
    private double extraAmount;

    @JsonProperty("final_amount")
    private double finalAmount;
}
