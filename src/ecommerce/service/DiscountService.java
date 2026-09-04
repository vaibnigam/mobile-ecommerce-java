package ecommerce.service;

import ecommerce.model.Discount;

public class DiscountService {

    public Discount getDiscount(String code) {

        if (code == null || code.isBlank()) {
            return null;
        }

        if (code.equalsIgnoreCase("MOBILE10")) {
            return new Discount("MOBILE10", 10);
        }

        if (code.equalsIgnoreCase("WELCOME5")) {
            return new Discount("WELCOME5", 5);
        }

        throw new IllegalArgumentException("Invalid discount code.");
    }

    public double calculateDiscount(double amount, Discount discount) {

        if (discount == null) {
            return 0;
        }

        double discountAmount = discount.calculateDiscount(amount);

        return Math.min(discountAmount, amount);
    }
}