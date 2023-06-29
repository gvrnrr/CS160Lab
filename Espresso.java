/**
 * Gianni McGovern
 * CS160L Summer 2023
 */

import java.util.ArrayList;
import java.util.List;

public class Espresso implements Coffee {
    @Override
    public double getCost() {
        return 1.75;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = getIngredients();
        ingredients.add("Espresso");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return "An espresso";
    }
}
