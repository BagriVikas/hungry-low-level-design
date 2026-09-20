package decorator;

import enums.Ingredient;
import template.Coffee;

import java.util.Map;

public abstract class Condiment extends Coffee {

    // object that is being decorated
    private Coffee coffee;

    public Condiment(String name, int cost, Map<Ingredient, Integer> ingredients, Coffee coffee) {
        super(name, cost, ingredients);
        this.coffee = coffee;
    }

    @Override
    public void brewCoffee() {
        coffee.brewCoffee();
        System.out.println("Adding " + super.getDescription() + "...");
    }

    @Override
    public int getCost() {
        return coffee.getCost() + super.getCost();
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ": " + super.getDescription();
    }

    @Override
    public Map<Ingredient, Integer> getIngredients() {

        Map<Ingredient, Integer> allIngredients = this.coffee.getIngredients();
        // add your own ingredients also
        for (Map.Entry<Ingredient, Integer> ingredients: super.getIngredients().entrySet()) {
            Ingredient ingredient = ingredients.getKey();
            int quantity = ingredients.getValue();
            allIngredients.put(ingredient, allIngredients.getOrDefault(ingredient, 0) + quantity);
        }
        return allIngredients;

    }

}
