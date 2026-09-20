package state;

import enums.CoffeeType;
import enums.CondimentType;
import enums.Ingredient;
import inventory.Inventory;
import template.Coffee;

import java.util.List;
import java.util.Map;

public class CoffeeVendingMachine {

    private final Inventory inventory;
    private int moneyReceived;
    private Coffee selectedCoffee;
    private MachineState currentState;
    private static CoffeeVendingMachine INSTANCE;

    private CoffeeVendingMachine() {
        this.inventory = Inventory.getInstance();
        this.moneyReceived = 0;
        this.selectedCoffee = null;
        this.currentState = new IdleMachineState(this);
    }

    public static CoffeeVendingMachine getInstance() {

        if (null == INSTANCE) {
            INSTANCE = new CoffeeVendingMachine();
        }
        return INSTANCE;

    }

    public void selectCoffee(CoffeeType coffeeType, List<CondimentType> condiments) {
        currentState.selectCoffee(coffeeType, condiments);
    }

    public void insertCoin(int coin) {
        currentState.insertCoin(coin);
    }

    public void prepareCoffee() {
        currentState.prepareCoffee();
    }

    public void addStock(Ingredient ingredient, int quantity) {
        inventory.addStock(ingredient, quantity);
    }

    public void removeStock(Map<Ingredient, Integer> ingredients) {

        for (Map.Entry<Ingredient, Integer> ingredientAndQuant: ingredients.entrySet()) {
            inventory.removeStock(ingredientAndQuant.getKey(), ingredientAndQuant.getValue());
        }

    }

    public boolean hasEnoughStock(Map<Ingredient, Integer> requiredIngredients) {
        return inventory.hasEnoughStock(requiredIngredients);
    }

    public void setNextState(MachineState nextState) {
        this.currentState = nextState;
    }

    public void reset() {
        this.moneyReceived = 0;
        this.selectedCoffee = null;
        this.currentState = new IdleMachineState(this);
    }

    public void setMoneyReceived(int amount) {
        this.moneyReceived = amount;
    }

    public void setSelectedCoffee(Coffee coffee) {
        this.selectedCoffee = coffee;
    }

    public Coffee getSelectedCoffee() {
        return this.selectedCoffee;
    }

    public int getMoneyReceived() {
        return this.moneyReceived;
    }

}
