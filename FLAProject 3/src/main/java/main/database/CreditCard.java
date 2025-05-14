package main.database;

public class CreditCard {
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private double amount;

    public CreditCard(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.amount = 1000000; // Initial balance Rp.1.000.000
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CreditCard [number=" + cardNumber + ", expiry=" + expiryDate + "]";
    }
}
