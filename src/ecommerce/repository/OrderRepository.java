package ecommerce.repository;

import ecommerce.model.Order;
import java.util.*;

public class OrderRepository {

    private Map<Long, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Order findById(long orderId) {
        return orders.get(orderId);
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public List<Order> findByUserId(long userId) {

        List<Order> userOrders = new ArrayList<>();

        for (Order order : orders.values()) {
            if (order.getUserId() == userId) {
                userOrders.add(order);
            }
        }

        return userOrders;
    }
}