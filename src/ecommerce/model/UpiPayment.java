package ecommerce.model;

public class UpiPayment extends Payment {
    private final String upiId;

    public UpiPayment(double amount, String upiId) {
        super(amount);

        if (upiId == null || !upiId.matches("^[A-Za-z0-9._-]{2,}@[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Invalid UPI ID.");
        }

        this.upiId = upiId;
    }

    public String getUpiId() { return upiId; }

    @Override
    public boolean processPayment() {
        return true;
    }
}
