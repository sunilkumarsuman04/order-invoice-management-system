package com.invoicepayment.dto.request;

import lombok.Data;

@Data
public class AdminAuthRequestDTO {
    private String username;
    private String password;
}
