package ecommerce.repository;

import ecommerce.model.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCartRepository implements CartRepository {

    private final Map<Long, Cart> carts;

    public InMemoryCartRepository() {
        this.carts = new HashMap<>();
    }

    @Override
    public void save(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }

        carts.put(cart.getCustomerId(), cart);
    }

    @Override
    public Optional<Cart> findByCustomerId(long customerId) {
        return Optional.ofNullable(carts.get(customerId));
    }

    @Override
    public List<Cart> findAll() {
        return new ArrayList<>(carts.values());
    }

    @Override
    public void deleteByCustomerId(long customerId) {
        carts.remove(customerId);
    }

    @Override
    public boolean existsByCustomerId(long customerId) {
        return carts.containsKey(customerId);
    }
}