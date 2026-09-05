package ecommerce.repository;

import ecommerce.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository {

    void save(Cart cart);

    Optional<Cart> findByCustomerId(long customerId);

    List<Cart> findAll();

    void deleteByCustomerId(long customerId);

    boolean existsByCustomerId(long customerId);
}