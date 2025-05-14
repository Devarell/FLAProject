package main.business;

import main.business.MenuItem;

public abstract class Restaurant {
    public MenuItem orderSoto(String type) {
        System.out.println("Ordering Soto...");
        MenuItem soto = createSoto(type);
        System.out.println("Your " + soto.getName() + " is ready!");
        return soto;
    }

    protected abstract MenuItem createSoto(String type);
}
