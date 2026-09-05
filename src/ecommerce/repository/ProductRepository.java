package ecommerce.repository;

import ecommerce.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(Product product);

    Optional<Product> findById(long productId);

    List<Product> findAll();

    List<Product> findByName(String name);

    List<Product> findByBrand(String brand);

    void deleteById(long productId);

    boolean existsById(long productId);
}