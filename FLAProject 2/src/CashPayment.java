package business;

public class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Original amount: $" + String.format("%.2f", amount));
        System.out.println("Paid $" + String.format("%.2f", amount) + " in cash.");
    }
}
