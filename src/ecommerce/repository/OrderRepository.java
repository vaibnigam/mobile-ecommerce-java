package ecommerce.repository;

import ecommerce.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    void save(Order order);

    Optional<Order> findById(long orderId);

    List<Order> findAll();

    List<Order> findByCustomerId(long customerId);

    void deleteById(long orderId);

    boolean existsById(long orderId);
}