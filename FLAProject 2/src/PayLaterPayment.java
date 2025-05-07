package business;

public class PayLaterPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Payment of $" + amount + " scheduled using PayLater.");
    }
}