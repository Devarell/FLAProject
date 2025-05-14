package main.database;

import main.business.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderDatabase {
    private static OrderDatabase instance;
    private List<Order> orders;

    private OrderDatabase() {
        orders = new ArrayList<>();
    }

    public static OrderDatabase getInstance() {
        if (instance == null) {
            instance = new OrderDatabase();
        }
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public double calculateMonthlyRevenue() {
        return orders.stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }
}
