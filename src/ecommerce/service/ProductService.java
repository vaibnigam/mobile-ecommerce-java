package ecommerce.service;

import ecommerce.model.Product;
import ecommerce.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {

        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero.");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }

        productRepository.save(product);
    }

    public Product getProduct(long productId) {

        Product product = productRepository.findById(productId);

        if (product == null) {
            throw new IllegalArgumentException("Product not found.");
        }

        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void updateStock(long productId, int stock) {

        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }

        Product product = getProduct(productId);
        product.setStock(stock);
    }

    public void deleteProduct(long productId) {

        getProduct(productId);
        productRepository.delete(productId);
    }
}