/**
 * Gianni McGovern
 * CS160L Summer 2023
 */

import java.util.List;
public class WithSugar extends CoffeeDecorator {
    public WithSugar(Coffee c){
        super(c);
    }
    @Override
    public double getCost(){
        return super.getCost() + 0.15;
    }
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add("Sugar");
        return ingredients;
    }
    @Override
    public String printCoffee() {
        return super.printCoffee() + " with sugar";
    }

}
