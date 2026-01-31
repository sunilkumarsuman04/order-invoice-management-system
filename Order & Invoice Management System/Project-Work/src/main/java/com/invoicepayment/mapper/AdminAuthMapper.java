package com.invoicepayment.mapper;

import com.invoicepayment.dto.request.AdminAuthRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminAuthMapper {

    AdminAuthRequestDTO toDto(String username, String password);
}
