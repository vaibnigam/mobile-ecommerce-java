package ecommerce.service;

import ecommerce.exception.ProductNotFoundException;
import ecommerce.model.Product;
import ecommerce.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        if (product.getBrand() == null || product.getBrand().isBlank()) {
            throw new IllegalArgumentException("Product brand cannot be empty.");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero.");
        }
        if (product.getRam() <= 0 || product.getStorage() <= 0) {
            throw new IllegalArgumentException("RAM and storage must be greater than zero.");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
        productRepository.save(product);
    }

    public Product getProduct(long productId) {
        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new ProductNotFoundException(
                    "Product not found with ID: " + productId);
        }
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchProducts(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAllProducts();
        }

        String text = keyword.trim().toLowerCase();
        List<Product> results = new ArrayList<>();

        for (Product product : productRepository.findAll()) {
            if (product.getBrand().toLowerCase().contains(text)
                    || product.getName().toLowerCase().contains(text)) {
                results.add(product);
            }
        }
        return results;
    }

    public void updateStock(long productId, int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
        getProduct(productId).setStock(stock);
    }

    public void deleteProduct(long productId) {
        getProduct(productId);
        productRepository.delete(productId);
    }
}
