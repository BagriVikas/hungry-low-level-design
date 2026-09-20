package inventory;

import enums.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    public static Inventory INSTANCE;
    private Map<Ingredient, Integer> ingredientsStock;

    private Inventory() {
        ingredientsStock = new HashMap<>();
    }

    public static Inventory getInstance() {

        if (null == INSTANCE) {
            INSTANCE = new Inventory();
        }
        return INSTANCE;

    }

    public void addStock(Ingredient ingredient, int quantity) {
        ingredientsStock.put(ingredient, ingredientsStock.getOrDefault(ingredient, 0) + quantity);
    }

    public void removeStock(Ingredient ingredient, int quantity) {
        ingredientsStock.put(ingredient, ingredientsStock.get(ingredient) - quantity);
    }

    public boolean hasEnoughStock(Map<Ingredient, Integer> requiredIngredients) {

        boolean hasEnoughStock = true;
        for (Map.Entry<Ingredient, Integer> ingAndQuant: requiredIngredients.entrySet()) {
            Ingredient ing = ingAndQuant.getKey();
            int requiredQuant = ingAndQuant.getValue();
            if (!ingredientsStock.containsKey(ing) || ingredientsStock.get(ing) < requiredQuant) {
                hasEnoughStock = false;
                break;
            }
        }
        return hasEnoughStock;

    }

}
