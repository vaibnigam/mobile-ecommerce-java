package ecommerce.model;

import ecommerce.enums.OrderStatus;
import ecommerce.enums.PaymentMethod;
import ecommerce.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Order {

    private final long id;
    private final long customerId;
    private final List<OrderItem> items;
    private final BigDecimal totalAmount;
    private final Address shippingAddress;
    private final PaymentMethod paymentMethod;
    private final LocalDateTime createdAt;

    private OrderStatus status;
    private PaymentStatus paymentStatus;

    public Order(
            long id,
            long customerId,
            List<OrderItem> items,
            Address shippingAddress,
            PaymentMethod paymentMethod,
            PaymentStatus paymentStatus) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Order ID must be greater than zero"
            );
        }

        if (customerId <= 0) {
            throw new IllegalArgumentException(
                    "Customer ID must be greater than zero"
            );
        }

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException(
                    "Order must contain at least one item"
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

        if (paymentStatus == null) {
            throw new IllegalArgumentException(
                    "Payment status cannot be null"
            );
        }

        this.id = id;
        this.customerId = customerId;
        this.items = Collections.unmodifiableList(
                new ArrayList<>(items)
        );
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.status = OrderStatus.PLACED;
        this.createdAt = LocalDateTime.now();

        this.totalAmount = calculateTotal();
    }

    public long getId() {
        return id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void updateStatus(OrderStatus status) {

        if (status == null) {
            throw new IllegalArgumentException(
                    "Order status cannot be null"
            );
        }

        this.status = status;
    }

    public void updatePaymentStatus(PaymentStatus paymentStatus) {

        if (paymentStatus == null) {
            throw new IllegalArgumentException(
                    "Payment status cannot be null"
            );
        }

        this.paymentStatus = paymentStatus;
    }

    private BigDecimal calculateTotal() {

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {
            total = total.add(item.getSubtotal());
        }

        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Order order)) {
            return false;
        }

        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                ", shippingAddress=" + shippingAddress +
                ", paymentMethod=" + paymentMethod +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}