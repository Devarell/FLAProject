package presentation;

import presentation.Food;

public class FoodFactory {
    public static Food createFood(String type) {
        switch (type.toLowerCase()) {
            case "pizza":
                return new Pizza();
            case "burger":
                return new Burger();
            default:
                throw new IllegalArgumentException("Unknown food type: " + type);
        }
    }
}

class Pizza extends Food {
    public Pizza() {
        this.name = "Pizza";
    }
}

class Burger extends Food {
    public Burger() {
        this.name = "Burger";
    }
}