package ecommerce.repository;

import ecommerce.model.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {
    private final Map<Long, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Order findById(long orderId) {
        return orders.get(orderId);
    }

    public List<Order> findAll() {
        List<Order> result = new ArrayList<>(orders.values());
        result.sort(Comparator.comparing(Order::getOrderDate).reversed());
        return result;
    }

    public List<Order> findByUserId(long userId) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getUserId() == userId) {
                result.add(order);
            }
        }
        result.sort(Comparator.comparing(Order::getOrderDate).reversed());
        return result;
    }
}
