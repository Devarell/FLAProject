package main.business;

import main.database.CreditCard;

public class PaymentByCreditCard implements PaymentStrategy {
    private CreditCard card;

    @Override
    public void collectPaymentDetails() {
        card = new CreditCard("1234-5678", "12/25", "123");
        System.out.println("Collecting Credit Card Details...");
    }

    @Override
    public boolean validatePaymentDetails() {
        System.out.println("Validating Credit Card Info: " + card);
        return true;
    }    @Override
    public void pay(double amount) {
        double discount = amount * 0.1; // 10% discount
        double finalAmount = amount - discount;
        System.out.println("Membayar Rp." + amount + " menggunakan Kartu Kredit");
        System.out.println("Diskon (10%): Rp." + discount);
        System.out.println("Total setelah diskon: Rp." + finalAmount);
        card.setAmount(card.getAmount() - finalAmount);
        System.out.println("Sisa saldo: Rp." + card.getAmount());
    }
}
