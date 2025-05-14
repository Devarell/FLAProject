package main.business;

public class SotoRestaurant extends Restaurant {
    @Override
    protected MenuItem createSoto(String type) {
        if (type.equalsIgnoreCase("ayam")) {
            return new SotoAyam();
        } else if (type.equalsIgnoreCase("daging")) {
            return new SotoDaging();
        }
        throw new IllegalArgumentException("Unknown soto type: " + type);
    }
}
