package ecommerce.service;

import ecommerce.model.Product;
import ecommerce.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {

        if (productRepository == null) {
            throw new IllegalArgumentException(
                    "Product repository cannot be null"
            );
        }

        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException(
                    "Product cannot be null"
            );
        }

        if (productRepository.existsById(product.getId())) {
            throw new IllegalArgumentException(
                    "Product already exists with ID: " + product.getId()
            );
        }

        productRepository.save(product);
    }

    public Product getProductById(long productId) {

        if (productId <= 0) {
            throw new IllegalArgumentException(
                    "Product ID must be greater than zero"
            );
        }

        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product not found with ID: " + productId
                        )
                );
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchByName(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Product name cannot be null or blank"
            );
        }

        return productRepository.findByName(name);
    }

    public List<Product> searchByBrand(String brand) {

        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException(
                    "Product brand cannot be null or blank"
            );
        }

        return productRepository.findByBrand(brand);
    }

    public void increaseStock(long productId, int quantity) {

        Product product = getProductById(productId);

        product.increaseStock(quantity);

        productRepository.save(product);
    }

    public void decreaseStock(long productId, int quantity) {

        Product product = getProductById(productId);

        product.reduceStock(quantity);

        productRepository.save(product);
    }

    public void deactivateProduct(long productId) {

        Product product = getProductById(productId);

        product.deactivate();

        productRepository.save(product);
    }

    public void activateProduct(long productId) {

        Product product = getProductById(productId);

        product.activate();

        productRepository.save(product);
    }
}