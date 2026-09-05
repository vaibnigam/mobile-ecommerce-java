package ecommerce.exception;

public class CartNotFoundException extends BusinessException {

    public CartNotFoundException(long customerId) {
        super("Cart not found for customer ID: " + customerId);
    }
}