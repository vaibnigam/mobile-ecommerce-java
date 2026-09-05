package ecommerce.service;

import ecommerce.enums.PaymentStatus;
import ecommerce.exception.InsufficientStockException;
import ecommerce.exception.InvalidCartException;
import ecommerce.model.*;
import ecommerce.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final DiscountService discountService;
    private final PaymentService paymentService;

    public OrderService(
            OrderRepository orderRepository,
            CartService cartService,
            DiscountService discountService,
            PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.discountService = discountService;
        this.paymentService = paymentService;
    }

    public Order checkout(
            long userId,
            Cart cart,
            String discountCode,
            Payment payment) {

        if (cart == null || cart.isEmpty()) {
            throw new InvalidCartException("Cart is empty.");
        }

        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null.");
        }

        validateStock(cart);

        double subtotal = cartService.getSubtotal(cart);

        Discount discount = discountService.getDiscount(discountCode);
        double discountAmount =
                discountService.calculateDiscount(subtotal, discount);

        double finalAmount = round(subtotal - discountAmount);

        if (Math.abs(payment.getAmount() - finalAmount) > 0.01) {
            throw new IllegalArgumentException(
                    "Payment amount does not match order amount.");
        }

        paymentService.processPayment(payment);

        List<OrderItem> orderItems = createOrderItems(cart);

        reduceStock(cart);

        long orderId = generateOrderId();

        Order order = new Order(
                orderId,
                userId,
                orderItems,
                subtotal,
                discountAmount,
                finalAmount
        );

        order.setPaymentStatus(PaymentStatus.SUCCESS);
        orderRepository.save(order);
        cartService.clearCart(cart);

        return order;
    }

    public double calculateDiscount(double subtotal, String discountCode) {
        Discount discount = discountService.getDiscount(discountCode);
        return round(discountService.calculateDiscount(subtotal, discount));
    }

    public Order getOrder(long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getUserOrders(long userId) {
        return orderRepository.findByUserId(userId);
    }

    private void validateStock(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (item.getQuantity() <= 0) {
                throw new InvalidCartException(
                        "Invalid quantity for " + product.getName());
            }

            if (item.getQuantity() > product.getStock()) {
                throw new InsufficientStockException(
                        "Insufficient stock for " + product.getName()
                                + ". Available: " + product.getStock());
            }
        }
    }

    private List<OrderItem> createOrderItems(Cart cart) {
        List<OrderItem> items = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            Product p = cartItem.getProduct();

            items.add(new OrderItem(
                    p.getId(),
                    p.getName(),
                    p.getBrand(),
                    p.getPrice(),
                    cartItem.getQuantity()
            ));
        }

        return items;
    }

    private void reduceStock(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            p.setStock(p.getStock() - item.getQuantity());
        }
    }

    private long generateOrderId() {
        long id = System.currentTimeMillis();
        while (orderRepository.findById(id) != null) {
            id++;
        }
        return id;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
