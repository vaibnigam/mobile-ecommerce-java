package ecommerce.service;

import ecommerce.enums.OrderStatus;
import ecommerce.enums.PaymentMethod;
import ecommerce.enums.PaymentStatus;
import ecommerce.model.Address;
import ecommerce.model.Cart;
import ecommerce.model.CartItem;
import ecommerce.model.Customer;
import ecommerce.model.Order;
import ecommerce.model.OrderItem;
import ecommerce.model.Product;
import ecommerce.repository.CartRepository;
import ecommerce.repository.CustomerRepository;
import ecommerce.repository.OrderRepository;
import ecommerce.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public OrderService(
            OrderRepository orderRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            CartRepository cartRepository) {

        if (orderRepository == null) {
            throw new IllegalArgumentException(
                    "Order repository cannot be null"
            );
        }

        if (customerRepository == null) {
            throw new IllegalArgumentException(
                    "Customer repository cannot be null"
            );
        }

        if (productRepository == null) {
            throw new IllegalArgumentException(
                    "Product repository cannot be null"
            );
        }

        if (cartRepository == null) {
            throw new IllegalArgumentException(
                    "Cart repository cannot be null"
            );
        }

        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public Order placeOrder(
            long orderId,
            long customerId,
            Address shippingAddress,
            PaymentMethod paymentMethod) {

        validateCustomer(customerId);

        if (orderId <= 0) {
            throw new IllegalArgumentException(
                    "Order ID must be greater than zero"
            );
        }

        if (shippingAddress == null) {
            throw new IllegalArgumentException(
                    "Shipping address cannot be null"
            );
        }

        if (paymentMethod == null) {
            throw new IllegalArgumentException(
                    "Payment method cannot be null"
            );
        }

        if (orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException(
                    "Order already exists with ID: " + orderId
            );
        }

        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Cart not found for customer: "
                                        + customerId
                        )
                );

        if (cart.isEmpty()) {
            throw new IllegalStateException(
                    "Cannot place order with an empty cart"
            );
        }

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {

            Product product = productRepository
                    .findById(cartItem.getProduct().getId())
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Product not found with ID: "
                                            + cartItem.getProduct().getId()
                            )
                    );

            if (!product.isActive()) {
                throw new IllegalStateException(
                        "Product is not active: "
                                + product.getName()
                );
            }

            if (product.getStockQuantity()
                    < cartItem.getQuantity()) {

                throw new IllegalStateException(
                        "Insufficient stock for product: "
                                + product.getName()
                );
            }

            OrderItem orderItem = new OrderItem(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    cartItem.getQuantity()
            );

            orderItems.add(orderItem);
        }

        Order order = new Order(
                orderId,
                customerId,
                orderItems,
                shippingAddress,
                paymentMethod,
                PaymentStatus.PENDING
        );

        for (CartItem cartItem : cart.getItems()) {

            Product product = productRepository
                    .findById(cartItem.getProduct().getId())
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Product not found"
                            )
                    );

            product.reduceStock(cartItem.getQuantity());

            productRepository.save(product);
        }

        orderRepository.save(order);

        cart.clear();

        cartRepository.save(cart);

        return order;
    }

    public Order getOrderById(long orderId) {

        if (orderId <= 0) {
            throw new IllegalArgumentException(
                    "Order ID must be greater than zero"
            );
        }

        return orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Order not found with ID: "
                                        + orderId
                        )
                );
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomer(long customerId) {

        validateCustomer(customerId);

        return orderRepository.findByCustomerId(customerId);
    }

    public void updateOrderStatus(
            long orderId,
            OrderStatus newStatus) {

        if (newStatus == null) {
            throw new IllegalArgumentException(
                    "Order status cannot be null"
            );
        }

        Order order = getOrderById(orderId);

        OrderStatus currentStatus = order.getStatus();

        validateStatusTransition(
                currentStatus,
                newStatus
        );

        order.updateStatus(newStatus);

        orderRepository.save(order);
    }

    public void updatePaymentStatus(
            long orderId,
            PaymentStatus paymentStatus) {

        if (paymentStatus == null) {
            throw new IllegalArgumentException(
                    "Payment status cannot be null"
            );
        }

        Order order = getOrderById(orderId);

        order.updatePaymentStatus(paymentStatus);

        orderRepository.save(order);
    }

    private void validateCustomer(long customerId) {

        if (customerId <= 0) {
            throw new IllegalArgumentException(
                    "Customer ID must be greater than zero"
            );
        }

        if (!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found with ID: "
                            + customerId
            );
        }
    }

    private void validateStatusTransition(
            OrderStatus currentStatus,
            OrderStatus newStatus) {

        boolean validTransition = switch (currentStatus) {

            case PLACED ->
                    newStatus == OrderStatus.CONFIRMED
                            || newStatus == OrderStatus.CANCELLED;

            case CONFIRMED ->
                    newStatus == OrderStatus.SHIPPED
                            || newStatus == OrderStatus.CANCELLED;

            case SHIPPED ->
                    newStatus == OrderStatus.DELIVERED;

            case DELIVERED, CANCELLED ->
                    false;
        };

        if (!validTransition) {
            throw new IllegalStateException(
                    "Invalid order status transition: "
                            + currentStatus
                            + " -> "
                            + newStatus
            );
        }
    }
}