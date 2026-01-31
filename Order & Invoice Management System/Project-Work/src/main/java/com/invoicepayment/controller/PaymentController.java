package com.invoicepayment.controller;

import com.invoicepayment.dto.response.PaymentResponse;
import com.invoicepayment.entity.Payment;
import com.invoicepayment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // PAY USING INVOICE ID
    @PostMapping
    public PaymentResponse makePayment(
            @RequestBody Payment payment) {

        return paymentService.processPayment(payment);
    }
}
