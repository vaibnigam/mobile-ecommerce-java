package ecommerce.model;

import ecommerce.enums.ProductCategory;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private final long id;
    private final String brand;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final ProductCategory category;

    private int stockQuantity;
    private boolean active;

    public Product(
            long id,
            String brand,
            String name,
            String description,
            BigDecimal price,
            ProductCategory category,
            int stockQuantity,
            boolean active) {

        if (id <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than zero");
        }

        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be null or blank");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Product description cannot be null or blank");
        }

        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }

        if (category == null) {
            throw new IllegalArgumentException("Product category cannot be null");
        }

        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }

        this.id = id;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public boolean isActive() {
        return active;
    }

    public void reduceStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        if (quantity > stockQuantity) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        stockQuantity -= quantity;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        stockQuantity += quantity;
    }

    public void deactivate() {
        active = false;
    }

    public void activate() {
        active = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Product product)) {
            return false;
        }

        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", stockQuantity=" + stockQuantity +
                ", active=" + active +
                '}';
    }
}