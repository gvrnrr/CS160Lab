/**
 * Gianni McGovern
 * CS160L Summer 2023
 */
import java.io.*;
import java.util.*;

public class Main {
    private static Map<String, Integer> inventory = new TreeMap<String, Integer>(); // Represents the inventory of ingredients in the coffee shop
    private static List<CoffeeOrder> orders = new ArrayList<CoffeeOrder>(); // Represents the list of coffee orders placed by customers
    private static String logFile = "OrderLog.txt";
    private static String inventoryFile = "Inventory.txt"; //update inventoryFile to be "Inventory.txt"

    /**
     * The main method of the application. Driver method
     *
     * @param args command-line arguments
     * @throws Exception e
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Java Coffee Co.!");
        try (Scanner input = new Scanner(System.in)) {
            boolean exitApp = false;
            while (!exitApp) {
                System.out.println("1. New Order\n" + "2. Reload Inventory\n" + "3. Upload Inventory\n" + "4. Update Order Log\n" + "5. Exit Application\n");
                int option = 0;
                while (option < 1 || option > 5) {
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        option = input.nextInt();
                        if (option < 1 || option > 5) {
                            System.out.println("Please enter a valid option.");
                        }
                    }
                }
                input.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        CoffeeOrder order = buildOrder();
                        orders.add(order);
                        orderPreference(order);
                        System.out.println(order.printOrder());
                        break;
                    case 2:
                        inventory = readInventory(inventoryFile);
                        System.out.println("Current Inventory:");
                        //printInventoryList();
                        break;
                    case 3:
                        writeInventory(inventoryFile);
                        break;
                    case 4:
                        writeOrderLog(logFile);
                        break;
                    case 5:
                        writeInventory(inventoryFile);
                        writeOrderLog(logFile);
                        exitApp = true;
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Builds a coffee order based on user input
     *
     * @return the constructed CoffeeOrder object
     */
    private static CoffeeOrder buildOrder() {
        CoffeeOrder order = new CoffeeOrder();
        try {
            Scanner input = new Scanner(System.in);
            boolean addCoffee = true;
            while (addCoffee) {
                // prompt user to select base coffee type
                System.out.println("Select coffee type:");
                System.out.println("\t1. Black Coffee");
                System.out.println("\t2. Espresso");
                Coffee coffee;

                int option = 0;
                while (option < 1 || option > 2) {
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        option = input.nextInt();
                        if (option < 1 || option > 2) System.out.println("Please enter a valid option.");
                    }
                }
                input.nextLine(); // nextInt() doesn't consume newline
                if (option == 2) {
                    // Espresso is a specific case
                    coffee = new Espresso();
                } else {
                    // make BlackCoffee the default case
                    coffee = new BlackCoffee();
                }

                // prompt user for any customizations
                while (option != 0) {
                    System.out.println(String.format("Coffee brewing: %s.", coffee.printCoffee()));
                    System.out.println("Would you like to add anything to your coffee?");
                    System.out.println("\t1. Flavored Syrup");
                    System.out.println("\t2. Hot Water");
                    System.out.println("\t3. Milk");
                    System.out.println("\t4. Sugar");
                    System.out.println("\t5. Whipped Cream");
                    System.out.println("\t0. NO - Finish Coffee");

                    while (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    }
                    option = input.nextInt();
                    input.nextLine();
                    coffee = switch (option) {
                        case 1 -> {
                            System.out.println("Please select a flavor:");
                            for (WithFlavor.Syrup flavor : WithFlavor.Syrup.values()) {
                                System.out.println("\t" + String.format("%d. %s", flavor.ordinal() + 1, flavor));
                            }
                            int max = WithFlavor.Syrup.values().length;
                            option = 0;
                            while (option < 1 || option > max) {
                                if (!input.hasNextInt()) {
                                    System.out.println("Please enter a valid number.");
                                    input.nextLine();
                                } else {
                                    option = input.nextInt();
                                    if (option < 1 || option > max) System.out.println("Please enter a valid option.");
                                }
                            }
                            input.nextLine();
                            WithFlavor.Syrup flavor = WithFlavor.Syrup.values()[option - 1];
                            option = 1;
                            yield new WithFlavor(coffee, flavor);
                        }
                        case 2 -> new WithHotWater(coffee);
                        case 3 -> new WithMilk(coffee);
                        case 4 -> new WithSugar(coffee);
                        case 5 -> new WithWhippedCream(coffee);
                        default -> {
                            if (option != 0) System.out.println("Please enter valid option.");
                            yield coffee;
                        }
                    };
                }
                order.addCoffee(coffee);

                System.out.println("Would you like to order another coffee (Y or N)?");
                String yn = input.nextLine();
                while (!(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("Y"))) {
                    System.out.println("Please enter Y or N.");
                    yn = input.nextLine();
                }
                addCoffee = !yn.equalsIgnoreCase("N");
            }
        } catch (Exception e) {
            System.out.println("Error building order: " + e.getMessage());
        }
        return order;
    }

    /**
     * Reads the inventory from a file.
     *
     * @param filePath the path of the inventory file
     * @return map containing the inventory data
     */
    private static Map<String, Integer> readInventory(String filePath) {
        Map<String, Integer> inventory = new TreeMap<>();
        try(BufferedReader fileReader = new BufferedReader((new FileReader(filePath)))){
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] g = line.split(" = ");
                String ingredient = g[0].trim();
                int quantity = Integer.parseInt(g[1].trim());
                inventory.put(ingredient, quantity);
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory: " + e.getMessage());
            e.printStackTrace();
        }
        return inventory;
    }

    /**
     * Writes the inventory to a file.
     *
     * @param filePath the path of the inventory file
     */
    private static void writeInventory(String filePath) {
        String[] ingredients = {
                "Black Coffee",
                "CARAMEL Syrup",
                "Espresso",
                "Hot Water",
                "Milk",
                "MOCHA Syrup",
                "Sugar",
                "VANILLA Syrup",
                "Whipped Cream"
        };
        int[] quantity = {
                // AMOUNTS
                10,  // Black Coffee
                5,   // CARAMEL Syrup
                10,  // Espresso
                100, // Hot Water
                10,  // Milk
                5,   // MOCHA Syrup
                10,  // Sugar
                5,   // VANILLA Syrup
                5    // Whipped Cream
        };
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < ingredients.length; i++) {
                writer.write(ingredients[i] + " = " + quantity[i]);
                writer.newLine();
            }
            System.out.println("Inventory has been successfully updated.");
        } catch (IOException e) {
            System.out.println("Error writing inventory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<CoffeeOrder> readOrderLog(String filePath) {
        return null;
    }

    /**
     * Writes the order log to file.
     *
     * @param filePath the path of the order log file
     */
    private static void writeOrderLog(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (CoffeeOrder order : orders) {
                writer.write(order.printOrder());
                writer.newLine();
                System.out.println("Order log successfully written:  ");
            }
            orders.clear();
        } catch (Exception e) {
            System.out.println("Error writing order log: " + e.getMessage());
        }
    }
    /**
     * Checks if an ingredient is available in the inventory.
     *
     * @param ingredient the ingredient to check
     * @return true if the ingredient is available, false otherwise
     */
    private static boolean isInInventory(String ingredient) {
        for(String g : inventory.keySet()) {
            if (!g.equals(ingredient) && (inventory.get(g) > 0)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Prompts the user whether they want to dine in or not, and calls preference method to update your receipt accordingly.
     *
     * @param order takes in the customers order
     */
    private static void orderPreference(CoffeeOrder order){
        Scanner scan = new Scanner(System.in);
        int input;
        System.out.println("""
        Would you like to dine in or take this order to go? 
          1. Dine In!
          2. ToGo!
        """);
        input = scan.nextInt();
        order.preference(input);

        // Prompt for discount code
        scan.nextLine(); // Consume newline
        System.out.println("Do you have a discount code? (Y or N)");
        String hasDiscountCode = scan.nextLine();
        if (hasDiscountCode.equalsIgnoreCase("Y")) {
            System.out.println("Enter the discount code:");
            String discountCode = scan.nextLine();
            if (discountCode.equalsIgnoreCase("JD011")) {
                double discountAmount = order.getTotal() * 0.1;
                double discountedTotal = order.getTotal() - discountAmount;
                order.setDiscountAmount(discountAmount); // Set the discount amount in the CoffeeOrder object
            } else {
                System.out.println("Invalid discount code. No discount applied.");
            }
        }
    }
}

