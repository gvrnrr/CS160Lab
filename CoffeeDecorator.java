/**
 * Gianni McGovern
 * CS160L Summer 2023
 */

import java.util.List;

public abstract class CoffeeDecorator implements Coffee {
    private Coffee coffee;

    public CoffeeDecorator(Coffee c) {
        coffee = c;
    }

    public double getCost() {
        return coffee.getCost();
    }
    public List<String> getIngredients() {
        return coffee.getIngredients();
    }
    public String printCoffee() {
        return coffee.printCoffee();
    }
}