package ecommerce.exception;

public class OrderNotFoundException extends BusinessException {

    public OrderNotFoundException(long orderId) {
        super("Order not found with ID: " + orderId);
    }
}