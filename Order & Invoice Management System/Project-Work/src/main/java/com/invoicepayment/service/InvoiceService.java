package com.invoicepayment.service;

import com.invoicepayment.dto.request.BuyProductRequestDTO;
import com.invoicepayment.dto.response.InvoiceResponseDTO;
import com.invoicepayment.dto.response.OrderItemResponseDTO;
import com.invoicepayment.entity.Product;
import com.invoicepayment.exception.InsufficientStockException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {

    private final ProductService productService;
    private final DiscountService discountService;

    private final Map<Long, InvoiceResponseDTO> invoiceStore = new HashMap<>();
    private Long invoiceIdCounter = 100L;

    public InvoiceService(ProductService productService,
                          DiscountService discountService) {
        this.productService = productService;
        this.discountService = discountService;
    }

    // ================= SINGLE PRODUCT BUY =================
    public InvoiceResponseDTO generateInvoice(Long productId) {

        Product product = productService.getProductEntity(productId);

        // validate
        if (product.getProductQuantity() <= 0) {
            throw new InsufficientStockException(
                    "Product is out of stock"
            );
        }

        InvoiceResponseDTO response = new InvoiceResponseDTO();
        Long invoiceId = ++invoiceIdCounter;
        response.setInvoiceId(invoiceId);

        double total = product.getProductPrice(); // quantity = 1
        double discount = total * discountService.getDiscountRate(total);

        response.setProductId(product.getProductId());
        response.setProductName(product.getProductName());
        response.setProductPrice(product.getProductPrice());
        response.setTotalPrice(total);
        response.setDiscountAmount(discount);
        response.setNetAmount(total - discount);

        if (product.getDescription() != null &&
                !product.getDescription().trim().isEmpty()) {
            response.setDescription(product.getDescription().trim());
        }

        invoiceStore.put(invoiceId, response);
        return response;
    }

    // add multiple product
    public InvoiceResponseDTO generateInvoice(
            List<BuyProductRequestDTO> requests) {

        InvoiceResponseDTO response = new InvoiceResponseDTO();
        Long invoiceId = ++invoiceIdCounter;
        response.setInvoiceId(invoiceId);

        List<OrderItemResponseDTO> items = new ArrayList<>();
        double totalPrice = 0;

        for (BuyProductRequestDTO req : requests) {

            Product product =
                    productService.getProductEntity(req.getProductId());

            int availableQty = product.getProductQuantity();
            int requestedQty = req.getQuantity();


            if (requestedQty > availableQty) {
                throw new InsufficientStockException(
                        "Only " + availableQty +
                                " pieces are available for product '" +
                                product.getProductName() +
                                "'. You cannot buy " + requestedQty
                );
            }

            double itemTotal =
                    product.getProductPrice() * requestedQty;

            OrderItemResponseDTO item = new OrderItemResponseDTO();
            item.setProductId(product.getProductId());
            item.setProductName(product.getProductName());
            item.setProductPrice(product.getProductPrice());
            item.setQuantity(requestedQty);
            item.setItemTotal(itemTotal);

            items.add(item);
            totalPrice += itemTotal;
        }

        double discount =
                totalPrice * discountService.getDiscountRate(totalPrice);

        response.setItems(items);
        response.setTotalPrice(totalPrice);
        response.setDiscountAmount(discount);
        response.setNetAmount(totalPrice - discount);

        invoiceStore.put(invoiceId, response);
        return response;
    }

    //invoce
    public InvoiceResponseDTO getInvoiceById(Long invoiceId) {
        return invoiceStore.get(invoiceId);
    }
}
