package ecommerce.repository;

import ecommerce.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> products;

    public InMemoryProductRepository() {
        this.products = new HashMap<>();
    }

    @Override
    public void save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        products.put(product.getId(), product);
    }

    @Override
    public Optional<Product> findById(long productId) {
        return Optional.ofNullable(products.get(productId));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> findByName(String name) {
        if (name == null || name.isBlank()) {
            return List.of();
        }

        String searchTerm = name.toLowerCase();

        return products.values()
                .stream()
                .filter(product ->
                        product.getName()
                                .toLowerCase()
                                .contains(searchTerm))
                .toList();
    }

    @Override
    public List<Product> findByBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            return List.of();
        }

        String searchTerm = brand.toLowerCase();

        return products.values()
                .stream()
                .filter(product ->
                        product.getBrand()
                                .toLowerCase()
                                .contains(searchTerm))
                .toList();
    }

    @Override
    public void deleteById(long productId) {
        products.remove(productId);
    }

    @Override
    public boolean existsById(long productId) {
        return products.containsKey(productId);
    }
}