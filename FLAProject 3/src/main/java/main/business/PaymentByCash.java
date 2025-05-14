package main.business;

public class PaymentByCash implements PaymentStrategy {
    @Override
    public void collectPaymentDetails() {
        System.out.println("Preparing cash payment...");
    }

    @Override
    public boolean validatePaymentDetails() {
        return true;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Membayar Rp." + amount + " menggunakan Cash");
        System.out.println("Pembayaran berhasil!");
    }
}
