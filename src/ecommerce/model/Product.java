package ecommerce.model;

public class Product {

    private long id;
    private String brand;
    private String name;
    private double price;
    private int ram;
    private int storage;
    private int stock;

    public Product(long id, String brand, String name, double price,
                   int ram, int storage, int stock) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.ram = ram;
        this.storage = storage;
        this.stock = stock;
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

    public double getPrice() {
        return price;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public int getStock() {
        return stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}