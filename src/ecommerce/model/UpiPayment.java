package ecommerce.model;

public class UpiPayment extends Payment {

    private String upiId;

    public UpiPayment(double amount, String upiId) {
        super(amount);
        this.upiId = upiId;
    }

    public String getUpiId() {
        return upiId;
    }

    @Override
    public boolean processPayment() {
        return true;
    }
}