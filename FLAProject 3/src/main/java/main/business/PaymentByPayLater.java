package main.business;

public class PaymentByPayLater implements PaymentStrategy {
    private static final double INTEREST_RATE = 0.05; // 5% interest

    @Override
    public void collectPaymentDetails() {
        System.out.println("Verifying PayLater eligibility...");
    }

    @Override
    public boolean validatePaymentDetails() {
        System.out.println("Validating PayLater limit...");
        return true;
    }

    @Override
    public void pay(double amount) {
        double totalAmount = amount * (1 + INTEREST_RATE);
        System.out.println("Membayar Rp." + amount + " menggunakan PayLater");
        System.out.println("Biaya tambahan (5%): Rp." + (amount * INTEREST_RATE));
        System.out.println("Total yang harus dibayar: Rp." + totalAmount);
        System.out.println("Pembayaran berhasil! Jatuh tempo 30 hari dari sekarang.");
    }
}
