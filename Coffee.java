/**
 * Gianni McGovern
 * CS160L Summer 2023
 */

import java.util.List;
interface Coffee {
    /**
     * Prints a description of the coffee.
     *
     * @return string representation of the coffee.
     */

    String printCoffee();
    /**
     * Gets the cost of coffee.
     *
     * @return cost of coffee.
     */

    double getCost();

    /**
     * Gets the list of ingredients
     *
     * @return A list of ingredients
     */

    List<String> getIngredients();


}