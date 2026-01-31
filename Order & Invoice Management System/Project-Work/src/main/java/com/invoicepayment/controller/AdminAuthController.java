package com.invoicepayment.controller;

import com.invoicepayment.dto.request.AdminAuthRequestDTO;
import com.invoicepayment.mapper.AdminAuthMapper;
import com.invoicepayment.service.AdminAuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;
    private final AdminAuthMapper adminAuthMapper;

    public AdminAuthController(AdminAuthService adminAuthService,
                               AdminAuthMapper adminAuthMapper) {
        this.adminAuthService = adminAuthService;
        this.adminAuthMapper = adminAuthMapper;
    }

    // sign up
    @PostMapping("/signup")
    public String signUp(@RequestBody AdminAuthRequestDTO dto) {
        return adminAuthService.signUp(dto);
    }

    // login
    @PostMapping("/login")
    public String login(
            @RequestHeader("username") String username,
            @RequestHeader("password") String password) {

        AdminAuthRequestDTO dto =
                adminAuthMapper.toDto(username, password);

        return adminAuthService.login(dto);
    }
}
