package ecommerce.repository;

import ecommerce.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    public void save(Product product) {
        products.put(product.getId(), product);
    }

    public Product findById(long id) {
        return products.get(id);
    }

    public List<Product> findAll() {
        List<Product> result = new ArrayList<>(products.values());
        result.sort(Comparator.comparingLong(Product::getId));
        return result;
    }

    public void delete(long id) {
        products.remove(id);
    }
}
