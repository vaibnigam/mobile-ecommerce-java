package ecommerce.service;

import ecommerce.model.Payment;

public class PaymentService {

    public boolean processPayment(Payment payment) {

        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null.");
        }

        return payment.processPayment();
    }
}