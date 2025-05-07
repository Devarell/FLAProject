package presentation;

import presentation.Food;

public class FoodFactory {
    public static Food createFood(String type, int quantity) {
        Food food;
        switch (type.toLowerCase()) {
            case "pizza":
                food = new Pizza();
                break;
            case "burger":
                food = new Burger();
                break;
            default:
                throw new IllegalArgumentException("Unknown food type: " + type);
        }
        food.setQuantity(quantity);
        return food;
    }
}

class Pizza extends Food {
    public Pizza() {
        this.name = "Pizza";
        this.price = 15.99;
    }
}

class Burger extends Food {
    public Burger() {
        this.name = "Burger";
        this.price = 8.99;
    }
}
