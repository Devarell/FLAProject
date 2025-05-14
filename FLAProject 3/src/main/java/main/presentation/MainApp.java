package main.presentation;

import main.business.*;
import main.database.OrderDatabase;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant sotoRestaurant = new SotoRestaurant();
        Order order = new Order();
        
        while (true) {
            try {
                System.out.println("\n=== Menu Roemah Soto ===");
                System.out.println("1. Soto Ayam (Rp. 25.000)");
                System.out.println("2. Soto Daging (Rp. 30.000)");
                System.out.println("3. Lihat Pesanan");
                System.out.println("4. Selesai dan Bayar");
                System.out.println("5. Laporan Penjualan");
                System.out.println("6. Keluar");
                System.out.print("Pilihan Anda: ");
                
                int choice = Integer.parseInt(scanner.nextLine());
                
                if (choice == 1) {
                    MenuItem sotoAyam = sotoRestaurant.orderSoto("ayam");
                    order.addItem(sotoAyam);
                    System.out.println("Soto Ayam ditambahkan ke pesanan.");
                } else if (choice == 2) {
                    MenuItem sotoDaging = sotoRestaurant.orderSoto("daging");
                    order.addItem(sotoDaging);
                    System.out.println("Soto Daging ditambahkan ke pesanan.");
                } else if (choice == 3) {
                    if (order.getItems().isEmpty()) {
                        System.out.println("Belum ada pesanan!");
                    } else {
                        System.out.println("\n=== Pesanan Anda ===");
                        for (MenuItem item : order.getItems()) {
                            System.out.println(item.getName() + " - Rp." + item.getPrice());
                        }
                        System.out.println("Total: Rp." + order.getTotalPrice());
                    }
                } else if (choice == 4) {
                    if (order.getTotalPrice() > 0) {
                        System.out.println("\n=== Metode Pembayaran ===");
                        System.out.println("1. Kartu Kredit (Diskon 10%)");
                        System.out.println("2. Cash");
                        System.out.println("3. PayLater (Bunga 5%)");
                        System.out.print("Pilihan pembayaran: ");
                        int paymentChoice = Integer.parseInt(scanner.nextLine());
                        
                        PaymentStrategy paymentStrategy = null;
                        switch(paymentChoice) {
                            case 1:
                                paymentStrategy = new PaymentByCreditCard();
                                break;
                            case 2:
                                paymentStrategy = new PaymentByCash();
                                break;
                            case 3:
                                paymentStrategy = new PaymentByPayLater();
                                break;
                            default:
                                System.out.println("Pilihan pembayaran tidak valid!");
                                continue;
                        }
                        
                        order.setPaymentStrategy(paymentStrategy);
                        System.out.println("\nTotal pesanan: Rp." + order.getTotalPrice());
                        order.processPayment();
                        OrderDatabase.getInstance().addOrder(order);
                        
                        // Create new order after successful payment
                        order = new Order();
                        System.out.println("Terima kasih telah memesan!");
                    } else {
                        System.out.println("Belum ada pesanan!");
                    }
                } else if (choice == 5) {
                    ReportTemplate report = new SalesReport();
                    report.generateReport();
                } else if (choice == 6) {
                    System.out.println("Terima kasih!");
                    break;
                } else {
                    System.out.println("Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid! Mohon masukkan angka.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        scanner.close();
    }
}
