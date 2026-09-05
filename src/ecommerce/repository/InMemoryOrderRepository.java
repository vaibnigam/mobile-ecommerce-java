package ecommerce.repository;

import ecommerce.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryOrderRepository implements OrderRepository {

    private final Map<Long, Order> orders;

    public InMemoryOrderRepository() {
        this.orders = new HashMap<>();
    }

    @Override
    public void save(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        orders.put(order.getId(), order);
    }

    @Override
    public Optional<Order> findById(long orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public List<Order> findByCustomerId(long customerId) {
        return orders.values()
                .stream()
                .filter(order ->
                        order.getCustomerId() == customerId)
                .toList();
    }

    @Override
    public void deleteById(long orderId) {
        orders.remove(orderId);
    }

    @Override
    public boolean existsById(long orderId) {
        return orders.containsKey(orderId);
    }
}