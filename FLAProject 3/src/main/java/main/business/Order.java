package main.business;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<MenuItem> items;
    private double totalPrice;
    private PaymentStrategy paymentStrategy;

    public Order() {
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addItem(MenuItem item) {
        items.add(item);
        totalPrice += item.getPrice();
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void processPayment() {
        if (paymentStrategy != null) {
            paymentStrategy.collectPaymentDetails();
            if (paymentStrategy.validatePaymentDetails()) {
                paymentStrategy.pay(totalPrice);
            }
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }    public List<MenuItem> getItems() {
        return items;
    }
    
    public void viewOrder() {
        if (items.isEmpty()) {
            System.out.println("Belum ada pesanan!");
            return;
        }
        
        System.out.println("\n=== Detail Pesanan ===");
        for (MenuItem item : items) {
            System.out.println(item.getName() + " - Rp." + item.getPrice());
        }
        System.out.println("Total: Rp." + getTotalPrice());
        System.out.println("===================");
    }
}
