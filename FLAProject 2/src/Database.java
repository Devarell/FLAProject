package data;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance;
    private List<String> orders;

    private Database() {
        orders = new ArrayList<>();
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void addOrder(String order) {
        orders.add(order);
    }

    public List<String> getOrders() {
        return orders;
    }
}