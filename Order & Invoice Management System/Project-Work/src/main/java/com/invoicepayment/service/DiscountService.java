package com.invoicepayment.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    public double getDiscountRate(double totalAmount) {

        if (totalAmount < 2500) {
            return 0.10;
        } else if (totalAmount < 5000) {
            return 0.15;
        } else {
            return 0.30;
        }
    }
}
