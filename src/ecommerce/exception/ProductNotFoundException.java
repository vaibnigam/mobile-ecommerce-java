package ecommerce.exception;

public class ProductNotFoundException extends ECommerceException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
