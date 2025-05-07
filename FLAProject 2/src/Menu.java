package presentation;

import business.PaymentStrategy;
import business.CashPayment;
import business.CardPayment;
import business.PayLaterPayment;
import data.Database;

import java.util.Scanner;

public class Menu {
    private Database database;
    private double totalAmount = 0;

    public Menu() {
        this.database = Database.getInstance();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Order");
            System.out.println("2. View Orders");
            System.out.println("3. Pay");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter food type (Pizza: $15.99/Burger: $8.99): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    Food food = FoodFactory.createFood(type, quantity);
                    database.addOrder(food.getName() + " x" + quantity + " ($" + String.format("%.2f", food.getTotalPrice()) + ")");
                    totalAmount += food.getTotalPrice();
                    System.out.println(food.getName() + " x" + quantity + " added to orders. Total: $" + String.format("%.2f", food.getTotalPrice()));
                    break;
                case 2:
                    System.out.println("Orders: " + database.getOrders());
                    System.out.println("Total amount: $" + String.format("%.2f", totalAmount));
                    break;
                case 3:
                    if (totalAmount <= 0) {
                        System.out.println("No orders to pay for!");
                        break;
                    }
                    System.out.println("Total amount to pay: $" + String.format("%.2f", totalAmount));
                    System.out.print("Choose payment method (1: Cash, 2: Card [20% discount], 3: PayLater [20% extra]): ");
                    int paymentChoice = scanner.nextInt();
                    PaymentStrategy strategy;
                    switch (paymentChoice) {
                        case 1:
                            strategy = new CashPayment();
                            break;
                        case 2:
                            strategy = new CardPayment();
                            break;
                        case 3:
                            strategy = new PayLaterPayment();
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid payment choice.");
                    }
                    strategy.pay(totalAmount);
                    totalAmount = 0; // Reset total after payment
                    database.clearOrders(); // Clear orders after payment
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
