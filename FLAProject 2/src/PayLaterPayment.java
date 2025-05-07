package business;

public class PayLaterPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Payment of $" + amount + " scheduled using PayLater.");
        double ExtraFees = amount * 1.2; // 20% extra fees
        System.out.println("Original amount: $" + String.format("%.2f", amount));
        System.out.println("After 20% extra fees: $" + String.format("%.2f", ExtraFees));
        System.out.println("Paid $" + String.format("%.2f", ExtraFees) + " using PayLater.");
    }
}
