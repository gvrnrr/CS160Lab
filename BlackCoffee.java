/**
 * Gianni McGovern
 * CS160L Summer 2023
 */

import java.util.List;

public class BlackCoffee implements Coffee {
    @Override
    public double getCost() {
        return 1.0;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = getIngredients();
        ingredients.add("Black Coffee");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return "A black coffee";
    }
}