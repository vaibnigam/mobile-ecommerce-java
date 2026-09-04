package ecommerce.model;

import ecommerce.enums.OrderStatus;
import ecommerce.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private long orderId;
    private long userId;
    private List<OrderItem> items;
    private double subtotal;
    private double discount;
    private double finalAmount;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;

    public Order(long orderId, long userId, List<OrderItem> items,
                 double subtotal, double discount, double finalAmount) {

        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.subtotal = subtotal;
        this.discount = discount;
        this.finalAmount = finalAmount;
        this.orderDate = LocalDateTime.now();
        this.orderStatus = OrderStatus.PLACED;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getUserId() {
        return userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}