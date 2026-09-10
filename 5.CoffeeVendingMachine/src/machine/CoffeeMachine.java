package machine;

import entity.Coffee;
import entity.Ingredient;
import enums.MeasurementUnit;
import state.IdleMachineState;
import state.MachineState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CoffeeMachine {

    private Map<String, Coffee> coffeeItems;
    private Map<String, Ingredient> ingredientsStock;
    private MachineState state;
    private Coffee selectedCoffee;
    private int moneyReceived;

    public Coffee getSelectedCoffee() {
        return selectedCoffee;
    }

    public void setSelectedCoffee(Coffee selectedCoffee) {
        this.selectedCoffee = selectedCoffee;
    }

    public int getMoneyReceived() {
        return moneyReceived;
    }

    public void setMoneyReceived(int moneyReceived) {
        this.moneyReceived = moneyReceived;
    }

    public CoffeeMachine() {
        this.state = new IdleMachineState(this);
        this.coffeeItems = new ConcurrentHashMap<>();
        this.ingredientsStock = new ConcurrentHashMap<>();
    }

    public void addCoffee(String name, int price, Map<String, Integer> ingredients) {

        if (!coffeeItems.containsKey(name)) {
            Coffee coffee = new Coffee(name, price, ingredients);
            coffeeItems.put(name, coffee);
        }

    }

    public void removeCoffee(String name) {

        if (coffeeItems.containsKey(name)) {
            coffeeItems.remove(name);
        }

    }

    public void addIngredient(String name, MeasurementUnit unit) {

        if (!ingredientsStock.containsKey(name)) {
            Ingredient ingredient = new Ingredient(name, unit);
            ingredientsStock.put(name, ingredient);
        }

    }

    public void addStockForIngredient(String name, int quantity) {

        if (ingredientsStock.containsKey(name)) {
            ingredientsStock.get(name).addStocks(quantity);
        }

    }

    public void reduceStockForIngredient(String ingredientName, int quantity) {

        if (ingredientsStock.containsKey(ingredientName)) {
            int currentQuantity = ingredientsStock.get(ingredientName).getQuantity();
            ingredientsStock.get(ingredientName).setQuantity(currentQuantity - quantity);
        }

    }

    public void removeIngredient(String name) {

        if (ingredientsStock.containsKey(name)) {
            ingredientsStock.remove(name);
        }

    }

    public void selectCoffee(String name) {
        state.selectCoffee(name);
    }

    public void insertCoin(int amount) {
        state.insertCoin(amount);
    }

    public void dispense() {
        state.dispense();
    }

    public void reset() {
        this.state = new IdleMachineState(this);
        this.selectedCoffee = null;
        this.moneyReceived = 0;
    }

    public Coffee getCoffee(String name) {
        return coffeeItems.get(name);
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    public boolean hasEnoughCoffeeIngredients(Coffee coffee) {

        boolean hasEnoughIngredientsAvailable = true;
        for (Map.Entry<String, Integer> ingredientAndQuantity: coffee.getIngredients().entrySet()) {
            String ingredient = ingredientAndQuantity.getKey();
            int requiredQuantity = ingredientAndQuantity.getValue();
            if (!ingredientsStock.containsKey(ingredient) || ingredientsStock.get(ingredient).getQuantity() < requiredQuantity) {
                hasEnoughIngredientsAvailable = false;
                break;
            }
        }
        return hasEnoughIngredientsAvailable;

    }

    public void displayAvailableCoffees() {

        for (Map.Entry<String, Coffee> nameAndCoffee: coffeeItems.entrySet()) {
            String coffeeName = nameAndCoffee.getKey();
            Coffee coffee = nameAndCoffee.getValue();
            if (hasEnoughCoffeeIngredients(coffee)) {
                System.out.println("Coffee: " + coffeeName + ", price: " + coffee.getPrice());
                System.out.println("Ingredients: ");
                String ingredientsList = coffee.getIngredients().keySet()
                        .stream()
                        .map(ingredient -> ingredient + "- " + coffee.getIngredients().get(ingredient))
                        .collect(Collectors.joining(", "));
                System.out.println(ingredientsList);
            }
        }

    }

}
