package com.invoicepayment.service;

import com.invoicepayment.dto.request.AdminAuthRequestDTO;
import com.invoicepayment.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminAuthService {

    private final Map<String, Admin> adminStore = new HashMap<>();
    private boolean isLoggedIn = false;
    private Long adminIdCounter = 1L;

    public String signUp(AdminAuthRequestDTO dto) {

        if (adminStore.containsKey(dto.getUsername())) {
            return "Admin already exists. Please login.";
        }

        Admin admin = new Admin();
        admin.setAdminId(adminIdCounter++);
        admin.setUsername(dto.getUsername());
        admin.setPassword(dto.getPassword());

        adminStore.put(dto.getUsername(), admin);
        return "Admin sign-up successful. Please login.";
    }

    public String login(AdminAuthRequestDTO dto) {

        Admin admin = adminStore.get(dto.getUsername());

        if (admin == null) {
            return "Admin not found. Please sign-up first.";
        }

        if (!admin.getPassword().equals(dto.getPassword())) {
            return "Invalid admin credentials";
        }

        isLoggedIn = true;
        return "Admin login successful";
    }

    // to check admin is loggedIn or not
    public boolean isAdminLoggedIn() {
        return isLoggedIn;
    }
}
