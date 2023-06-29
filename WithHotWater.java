/**
 * Gianni McGovern
 * CS160L Summer 2023
 */

import java.util.List;

public class WithHotWater extends CoffeeDecorator {
    public WithHotWater(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost();
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add("Hot Water");
        return ingredients;    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with hot water";
    }
}
