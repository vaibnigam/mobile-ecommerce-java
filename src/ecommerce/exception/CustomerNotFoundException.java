package ecommerce.exception;

public class CustomerNotFoundException extends BusinessException {

    public CustomerNotFoundException(long customerId) {
        super("Customer not found with ID: " + customerId);
    }
}