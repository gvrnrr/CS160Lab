/**
 * Gianni McGovern
 * CS160L Summer 2023
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CoffeeOrder {
    private List<Coffee> coffees;
    private LocalDateTime orderDate;
    private String preference;
    private double discountAmount;

    /**
     * Constructor for CoffeeOrder class. Initializes the list of coffees and sets the order date to the current date and time
     */
    public CoffeeOrder() {
        coffees = new ArrayList<Coffee>();
        orderDate = LocalDateTime.now();
    }

    /**
     * Constructor for CoffeeOrder class with a specified order date
     *
     * @param orderDate The date and time when the coffee order was placed
     */
    public CoffeeOrder(LocalDateTime orderDate) {
        coffees = new ArrayList<Coffee>();
        this.orderDate = orderDate;
    }

    /**
     * Adds a Coffee object to the coffee order
     *
     * @param c The Coffee object to add to the order
     */
    public void addCoffee(Coffee c) {
        coffees.add(c);
    }

    /**
     * Returns the list of Coffee objects in the coffee order
     *
     * @return The list of Coffee objects in the order
     */
    public List<Coffee> getCoffees() { return coffees; }

    /**
     * Returns the date and time when the coffee order was placed
     *
     * @return The order date and time.
     */
    public LocalDateTime getOrderDate() { return orderDate; }

    /**
     * Calculates and returns the total cost of the coffee order
     *
     * @return The total cost of the order
     */
    public double getTotal() {
        double total = 0;
        for (Coffee coffee : coffees) {
            total += coffee.getCost();
        }
        return total;
    }

    /**
     * Generates a formatted string representing the coffee order receipt
     *
     * @return The coffee order receipt as a string
     */
    public String printOrder() {
        StringBuilder order = new StringBuilder("ORDER RECEIPT\n");
        order.append(getPreference());
        order.append("\n");
        order.append(String.format("Timestamp: %s%n", orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma")))); // togo or for here
        for (int i = 0; i < coffees.size(); i++) {
            Coffee coffee = coffees.get(i);
            order.append(String.format("Item %d: %s - %.2f%n", i + 1, coffee.printCoffee(), coffee.getCost()));
        }
        order.append(String.format("TOTAL = %.2f%n", getTotal()));
        if (discountAmount > 0) {
            order.append(String.format("Discount Applied = %.2f%n", discountAmount));
            order.append(String.format("Discounted Total = %.2f%n", getTotal() - discountAmount));
        }
        return order.toString();
    }

    /**
     * Sets the customer's preference for the order (togo or dine in).
     *
     * @param choice The choice indicating the preference. Use 2 for togo, any other value for dine-in.
     * @return preference message.
     */
    public String preference(int choice){
        if(choice == 2){
            return preference = "Order TOGO!";
        }
        return preference = "Thank you for dining in!\nEnjoy 10% off your next order with CODE: JD011 at checkout";
    }

    /**
     * Retrieves the customer's preference for the coffee order.
     *
     * @return The preference message.
     */
    public String getPreference(){
        return preference;
    }

    /**
     * Sets the discount amount for the item.
     *
     * @param discountAmount the discount amount to be set
     */
    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
}