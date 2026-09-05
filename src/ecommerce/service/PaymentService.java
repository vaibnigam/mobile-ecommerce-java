package ecommerce.service;

import ecommerce.exception.PaymentException;
import ecommerce.model.Payment;

public class PaymentService {

    public boolean processPayment(Payment payment) {
        if (payment == null) {
            throw new PaymentException("Payment cannot be null.");
        }

        try {
            boolean successful = payment.processPayment();
            if (!successful) {
                throw new PaymentException("Payment failed.");
            }
            return true;
        } catch (IllegalArgumentException e) {
            throw new PaymentException(e.getMessage());
        }
    }
}
