package ecommerce.model;

public class Discount {
    private final String code;
    private final double percentage;

    public Discount(String code, double percentage) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Discount code cannot be empty.");
        }
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100.");
        }
        this.code = code.toUpperCase();
        this.percentage = percentage;
    }

    public String getCode() { return code; }
    public double getPercentage() { return percentage; }

    public double calculateDiscount(double amount) {
        return Math.min(amount, amount * percentage / 100.0);
    }
}
