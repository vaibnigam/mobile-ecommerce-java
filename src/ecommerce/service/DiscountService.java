package ecommerce.service;

import ecommerce.model.Discount;

import java.util.Map;

public class DiscountService {
    private final Map<String, Discount> discounts = Map.of(
            "MOBILE10", new Discount("MOBILE10", 10),
            "WELCOME5", new Discount("WELCOME5", 5),
            "SMART15", new Discount("SMART15", 15)
    );

    public Discount getDiscount(String code) {
        if (code == null || code.isBlank()) {
            return null;
        }

        Discount discount = discounts.get(code.trim().toUpperCase());

        if (discount == null) {
            throw new IllegalArgumentException("Invalid discount code.");
        }

        return discount;
    }

    public double calculateDiscount(double amount, Discount discount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        return discount == null ? 0 : discount.calculateDiscount(amount);
    }
}
