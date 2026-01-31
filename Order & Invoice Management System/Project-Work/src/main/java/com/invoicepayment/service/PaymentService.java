package com.invoicepayment.service;

import com.invoicepayment.dto.response.InvoiceResponseDTO;
import com.invoicepayment.dto.response.PaymentResponse;
import com.invoicepayment.entity.Payment;
import com.invoicepayment.entity.PaymentMethod;
import com.invoicepayment.exception.PaymentNotAllowedException;
import com.invoicepayment.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final InvoiceService invoiceService;
    private final PaymentMapper paymentMapper;

    public PaymentService(InvoiceService invoiceService,
                          PaymentMapper paymentMapper) {
        this.invoiceService = invoiceService;
        this.paymentMapper = paymentMapper;
    }

    public PaymentResponse processPayment(Payment payment) {

        Long invoiceId = payment.getInvoiceId();

        // 🔒 Validate invoice
        InvoiceResponseDTO invoice =
                invoiceService.getInvoiceById(invoiceId);

        if (invoice == null) {
            throw new PaymentNotAllowedException(
                    "Invalid invoice ID. Please generate invoice before payment."
            );
        }

        // ✅ Amount comes from invoice (NOT user)
        double amount = invoice.getNetAmount();
        PaymentMethod mode = payment.getPaymentMethod();

        double extraAmount = 0;
        double finalAmount = amount;

        switch (mode) {
            case CARD:
                extraAmount = amount * 0.02;
                finalAmount = amount + extraAmount;
                break;

            case UPI:
                extraAmount = 0;
                finalAmount = amount;
                break;

            case CASH:
                extraAmount = amount * 0.01;
                finalAmount = amount - extraAmount;
                break;
        }

        // MapStruct (base mapping)
        PaymentResponse response =
                paymentMapper.toResponse(payment);

        // Override calculated values
        response.setEnteredAmount(amount);
        response.setExtraAmount(extraAmount);
        response.setFinalAmount(finalAmount);

        return response;
    }
}
