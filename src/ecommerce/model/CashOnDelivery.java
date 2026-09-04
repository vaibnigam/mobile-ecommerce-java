package ecommerce.model;

public class CashOnDelivery extends Payment {

    public CashOnDelivery(double amount) {
        super(amount);
    }

    @Override
    public boolean processPayment() {
        return true;
    }
}