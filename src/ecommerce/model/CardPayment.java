package ecommerce.model;

public class CardPayment extends Payment {
    private final String cardNumber;
    private final String cvv;

    public CardPayment(double amount, String cardNumber, String cvv) {
        super(amount);

        String digits = cardNumber == null
                ? "" : cardNumber.replaceAll("\\s+", "");

        if (!digits.matches("\\d{16}")) {
            throw new IllegalArgumentException("Card number must contain 16 digits.");
        }

        if (cvv == null || !cvv.matches("\\d{3}")) {
            throw new IllegalArgumentException("CVV must contain 3 digits.");
        }

        this.cardNumber = digits;
        this.cvv = cvv;
    }

    public String getCardNumber() { return cardNumber; }

    public String getMaskedCardNumber() {
        return "************" + cardNumber.substring(12);
    }

    @Override
    public boolean processPayment() {
        return true;
    }
}
