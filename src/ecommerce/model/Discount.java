package ecommerce.model;

public class Discount {

    private String code;
    private double percentage;

    public Discount(String code, double percentage) {
        this.code = code;
        this.percentage = percentage;
    }

    public String getCode() {
        return code;
    }

    public double getPercentage() {
        return percentage;
    }

    public double calculateDiscount(double amount) {
        return amount * percentage / 100;
    }
}