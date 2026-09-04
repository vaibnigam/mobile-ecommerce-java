package ecommerce.model;

public class CardPayment extends Payment {

    private String cardNumber;

    public CardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public boolean processPayment() {
        return true;
    }
}