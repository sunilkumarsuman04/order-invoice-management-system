package com.invoicepayment.service;

import com.invoicepayment.dto.request.ProductRequestDTO;
import com.invoicepayment.dto.response.ProductResponseDTO;
import com.invoicepayment.entity.Product;
import com.invoicepayment.exception.ProductNotFoundException;
import com.invoicepayment.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final Map<Long, Product> productStore = new HashMap<>();
    private final ProductMapper productMapper;
    private final AdminAuthService adminAuthService;

    public ProductService(ProductMapper productMapper,
                          AdminAuthService adminAuthService) {
        this.productMapper = productMapper;
        this.adminAuthService = adminAuthService;
    }

    // Admin → add single product
    public String addProduct(ProductRequestDTO dto) {

        // check admin login
        if (!adminAuthService.isAdminLoggedIn()) {
            throw new RuntimeException(
                    "Admin not logged in. Please login first."
            );
        }

        productStore.put(dto.getProductId(), productMapper.toEntity(dto));
        return "Product added successfully";
    }

    // Admin → add multiple products
    public String addMultipleProducts(List<ProductRequestDTO> dtos) {

        // check admin login
        if (!adminAuthService.isAdminLoggedIn()) {
            throw new RuntimeException(
                    "Admin not logged in. Please login first."
            );
        }

        // Validate duplicates first
        for (ProductRequestDTO dto : dtos) {
            if (productStore.containsKey(dto.getProductId())) {
                throw new RuntimeException(
                        "Duplicate product ID: " + dto.getProductId()
                );
            }
        }

        // Save only after validation
        for (ProductRequestDTO dto : dtos) {
            productStore.put(
                    dto.getProductId(),
                    productMapper.toEntity(dto)
            );
        }

        return dtos.size() + " products added successfully";
    }

    //
    public Product getProductEntity(Long productId) {

        Product product = productStore.get(productId);

        if (product == null) {
            throw new ProductNotFoundException(
                    "Product not found with ID: " + productId
            );
        }
        return product;
    }

    // fetch product by id
    public ProductResponseDTO getProductById(Long productId) {

        Product product = getProductEntity(productId);

        ProductResponseDTO response =
                productMapper.toResponse(product);

        // check description
        if (response.getDescription() != null &&
                response.getDescription().trim().isEmpty()) {
            response.setDescription(null);
        }

        return response;
    }

    // fetch ALL products
    public List<ProductResponseDTO> getAllProducts() {

        if (productStore.isEmpty()) {
            throw new ProductNotFoundException("No products available");
        }

        List<ProductResponseDTO> responseList = new ArrayList<>();

        for (Product product : productStore.values()) {

            ProductResponseDTO response =
                    productMapper.toResponse(product);

            // Optional field handling
            if (response.getDescription() != null &&
                    response.getDescription().trim().isEmpty()) {
                response.setDescription(null);
            }

            responseList.add(response);
        }

        return responseList;
    }
}
