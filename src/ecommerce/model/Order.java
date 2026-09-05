package ecommerce.model;

import ecommerce.enums.OrderStatus;
import ecommerce.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final long orderId;
    private final long userId;
    private final List<OrderItem> items;
    private final double subtotal;
    private final double discount;
    private final double finalAmount;
    private final LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;

    public Order(long orderId, long userId, List<OrderItem> items,
                 double subtotal, double discount, double finalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = new ArrayList<>(items);
        this.subtotal = subtotal;
        this.discount = discount;
        this.finalAmount = finalAmount;
        this.orderDate = LocalDateTime.now();
        this.orderStatus = OrderStatus.PLACED;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public long getOrderId() { return orderId; }
    public long getUserId() { return userId; }
    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }
    public double getSubtotal() { return subtotal; }
    public double getDiscount() { return discount; }
    public double getFinalAmount() { return finalAmount; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public OrderStatus getOrderStatus() { return orderStatus; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }

    public void setOrderStatus(OrderStatus status) { this.orderStatus = status; }
    public void setPaymentStatus(PaymentStatus status) { this.paymentStatus = status; }
}
