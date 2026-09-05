package ecommerce.exception;

public class ProductNotFoundException extends BusinessException {

    public ProductNotFoundException(long productId) {
        super("Product not found with ID: " + productId);
    }
}