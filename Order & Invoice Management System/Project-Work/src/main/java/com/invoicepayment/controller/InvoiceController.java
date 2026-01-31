package com.invoicepayment.controller;

import com.invoicepayment.dto.response.InvoiceResponseDTO;
import com.invoicepayment.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // BUY PRODUCT → GENERATE INVOICE
    @PostMapping("/generate/{productId}")
    public InvoiceResponseDTO buyProduct(@PathVariable Long productId) {
        return invoiceService.generateInvoice(productId);
    }
}
