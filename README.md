- This program is used to create/store coffee orders

# How to run the application
- The application will prompt the user a menu with different options that they can choose
- The first option creates a new order and prompts the user with how they would like their coffee

# CoffeeOrder
- This class handles the addition of individual coffees to coffee orders and manages information such as total costs and receipts for orders.
- 
# Coffee <>
- This interface defines the methods printCoffee, getCost, and getIngredients, which are used throughout the program.
- 
# BlackCoffee
- This class implements the Coffee interface and serves as the foundation for creating a basic coffee drink.
- 
# Espresso
- Similar to BlackCoffee, this class implements the Coffee interface and serves as the foundation for creating an espresso-based drink.
- 
# CoffeeDecorator
- This class implements the Coffee interface and acts as the parent class for the decorator classes.
- 
# WithHotWater
- When invoked, this class adds hot water to the coffee order.
- 
# WithMilk
- When invoked, this class adds milk to the coffee order.
- 
# WithSugar
- When invoked, this class adds sugar to the coffee order.
- 
# WithWhippedCream
- When invoked, this class adds whipped cream to the coffee order.
- 
# WithFlavor
- When invoked, this class prompts the user to select a syrup flavor from a predefined set of options to be added to their coffee order.
