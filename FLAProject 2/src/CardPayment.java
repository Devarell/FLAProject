package business;

public class CardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        double discountedAmount = amount * 0.8; // 20% discount
        System.out.println("Original amount: $" + String.format("%.2f", amount));
        System.out.println("After 20% discount: $" + String.format("%.2f", discountedAmount));
        System.out.println("Paid $" + String.format("%.2f", discountedAmount) + " using Card.");
    }
}
