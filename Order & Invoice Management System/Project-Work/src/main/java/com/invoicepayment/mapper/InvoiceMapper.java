package com.invoicepayment.mapper;

import com.invoicepayment.dto.response.InvoiceResponseDTO;
import com.invoicepayment.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {


    InvoiceResponseDTO toInvoice(Product product);
}
