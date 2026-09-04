package ecommerce.service;

import ecommerce.enums.PaymentStatus;
import ecommerce.model.*;

import ecommerce.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private OrderRepository orderRepository;
    private CartService cartService;
    private DiscountService discountService;
    private PaymentService paymentService;

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
            throw new IllegalArgumentException("Cart is empty.");
        }

        double subtotal = cartService.getSubtotal(cart);

        Discount discount = discountService.getDiscount(discountCode);

        double discountAmount =
                discountService.calculateDiscount(subtotal, discount);

        double finalAmount = subtotal - discountAmount;

        if (Math.abs(payment.getAmount() - finalAmount) > 0.01) {
            throw new IllegalArgumentException(
                    "Payment amount does not match order amount."
            );
        }

        boolean paymentSuccessful =
                paymentService.processPayment(payment);

        if (!paymentSuccessful) {
            throw new IllegalArgumentException("Payment failed.");
        }

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {

            Product product = cartItem.getProduct();

            if (product.getStock() < cartItem.getQuantity()) {
                throw new IllegalArgumentException(
                        "Insufficient stock for " + product.getName()
                );
            }

            orderItems.add(
                    new OrderItem(
                            product.getId(),
                            product.getName(),
                            product.getBrand(),
                            product.getPrice(),
                            cartItem.getQuantity()
                    )
            );

            product.setStock(
                    product.getStock() - cartItem.getQuantity()
            );
        }

        long orderId = System.currentTimeMillis();

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

    public Order getOrder(long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getUserOrders(long userId) {
        return orderRepository.findByUserId(userId);
    }
}