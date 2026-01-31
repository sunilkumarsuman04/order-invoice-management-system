package com.invoicepayment.mapper;

import com.invoicepayment.dto.response.PaymentResponse;
import com.invoicepayment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "paymentMethod", target = "paymentMode")
    PaymentResponse toResponse(Payment payment);
}

