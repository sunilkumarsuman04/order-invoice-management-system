package com.invoicepayment.mapper;

import com.invoicepayment.dto.request.ProductRequestDTO;
import com.invoicepayment.dto.response.ProductResponseDTO;
import com.invoicepayment.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // DTO → Entity
    Product toEntity(ProductRequestDTO dto);

    // Entity → DTO (for response)
    ProductResponseDTO toResponse(Product product);
}
