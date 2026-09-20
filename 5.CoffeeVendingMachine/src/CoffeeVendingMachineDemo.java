import enums.CoffeeType;
import enums.CondimentType;
import enums.Ingredient;
import state.CoffeeVendingMachine;

import java.util.List;

public class CoffeeVendingMachineDemo {

    public static void main(String[] args) {

        CoffeeVendingMachine machine = CoffeeVendingMachine.getInstance();

        // --- Initial setup: Refill inventory ---
        System.out.println("=== Initializing Vending Machine ===");
        machine.addStock(Ingredient.COFFEE_BEANS, 50);
        machine.addStock(Ingredient.WATER, 500);
        machine.addStock(Ingredient.MILK, 6);
        machine.addStock(Ingredient.SUGAR, 100);

        // --- Scenario 1: Successful Purchase of a Latte ---
        System.out.println("\n--- SCENARIO 1: Buy a Latte (Success) ---");
        machine.selectCoffee(CoffeeType.LATTE, List.of());
        machine.insertCoin(12);
        machine.prepareCoffee();

        // --- Scenario 2: Purchase with Insufficient Funds & Cancellation ---
        System.out.println("\n--- SCENARIO 2: Buy Espresso (Insufficient Funds & Cancel) ---");
        machine.selectCoffee(CoffeeType.ESPRESSO, List.of());
        machine.insertCoin(10); // Price is 12

        // --- Scenario 3: Attempt to Buy with Insufficient Ingredients ---
        System.out.println("\n--- SCENARIO 3: Buy Cappuccino (Out of Milk) ---");
        machine.selectCoffee(CoffeeType.CAPPUCCINO, List.of(CondimentType.SUGAR_SYRUP, CondimentType.MOCHA));

        // --- Refill and final test ---
        System.out.println("\n--- REFILLING AND FINAL TEST ---");
        machine.addStock(Ingredient.MILK, 4);
        machine.selectCoffee(CoffeeType.LATTE, List.of(CondimentType.SUGAR_SYRUP));
        machine.insertCoin(250);
        machine.prepareCoffee();

    }

}
