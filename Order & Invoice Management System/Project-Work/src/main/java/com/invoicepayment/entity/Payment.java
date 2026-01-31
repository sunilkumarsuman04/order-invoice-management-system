package com.invoicepayment.entity;

import lombok.Data;

@Data
public class Payment {

    private Long invoiceId;
    private PaymentMethod paymentMethod;
}
