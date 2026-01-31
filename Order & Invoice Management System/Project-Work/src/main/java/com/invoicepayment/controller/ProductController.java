package com.invoicepayment.controller;

import com.invoicepayment.dto.request.BuyProductRequestDTO;
import com.invoicepayment.dto.request.ProductRequestDTO;
import com.invoicepayment.dto.response.InvoiceResponseDTO;
import com.invoicepayment.dto.response.ProductResponseDTO;
import com.invoicepayment.service.InvoiceService;
import com.invoicepayment.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final InvoiceService invoiceService;

    public ProductController(ProductService productService,
                             InvoiceService invoiceService) {
        this.productService = productService;
        this.invoiceService = invoiceService;
    }

    // ADMIN → add single product
    @PostMapping
    public String addProduct(@RequestBody ProductRequestDTO dto) {
        return productService.addProduct(dto);
    }

    // ADMIN → add multiple products
    @PostMapping("/bulk")
    public String addMultipleProducts(
            @RequestBody List<ProductRequestDTO> dtos) {

        return productService.addMultipleProducts(dtos);
    }

    // USER → buy product (GENERATE INVOICE)
    @PostMapping("/buy/{productId}")
    public InvoiceResponseDTO buyProduct(
            @PathVariable Long productId) {

        return invoiceService.generateInvoice(productId);
    }

    // USER → fetch product by id
    @GetMapping("/{productId}")
    public ProductResponseDTO getProductById(
            @PathVariable Long productId) {

        return productService.getProductById(productId);
    }

    @PostMapping("/buy")
    public InvoiceResponseDTO buyMultiple(
            @RequestBody List<BuyProductRequestDTO> requests) {

        return invoiceService.generateInvoice(requests);
    }


    // USER → fetch all products
    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }
}
