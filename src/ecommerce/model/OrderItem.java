package ecommerce.model;

public class OrderItem {

    private long productId;
    private String productName;
    private String brand;
    private double price;
    private int quantity;

    public OrderItem(long productId, String productName, String brand,
                     double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}