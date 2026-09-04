package ecommerce.repository;

import ecommerce.model.Product;
import java.util.*;

public class ProductRepository {

    private Map<Long, Product> products = new HashMap<>();

    public void save(Product product) {
        products.put(product.getId(), product);
    }

    public Product findById(long id) {
        return products.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public void delete(long id) {
        products.remove(id);
    }
}