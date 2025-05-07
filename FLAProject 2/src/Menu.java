package presentation;

import business.PaymentStrategy;
import business.CashPayment;
import business.CardPayment;
import business.PayLaterPayment;
import data.Database;

import java.util.Scanner;

public class Menu {
    private Database database;

    public Menu() {
        this.database = Database.getInstance();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Order");
            System.out.println("2. View Orders");
            System.out.println("3. Pay");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter food type (Pizza/Burger): ");
                    String type = scanner.nextLine();
                    Food food = FoodFactory.createFood(type);
                    database.addOrder(food.getName());
                    System.out.println(food.getName() + " added to orders.");
                    break;
                case 2:
                    System.out.println("Orders: " + database.getOrders());
                    break;
                case 3:
                    System.out.print("Enter payment amount: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Choose payment method (1: Cash, 2: Card, 3: PayLater): ");
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
                    strategy.pay(amount);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}