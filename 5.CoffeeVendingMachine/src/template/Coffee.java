package template;

import enums.Ingredient;

import java.util.Map;

public abstract class Coffee {

    private String name;
    private int cost;
    private Map<Ingredient, Integer> ingredients;

    public Coffee(String name, int cost, Map<Ingredient, Integer> ingredients) {
        this.name = name;
        this.cost = cost;
        this.ingredients = ingredients;
    }

    public final void prepare() {

        System.out.println("Start preparing coffee...");
        // Step I: take a cup of water
        System.out.println("Take a cup of water");
        // Step II: boil it
        System.out.println("Boil water");
        // Step III: brew coffee
        brewCoffee();
        // Step IV: pick a coffee cup
        System.out.println("Pick Coffee cup");
        // Step V: pour coffee into a cup
        System.out.println("Pour coffee into cup");

    }

    public abstract void brewCoffee();

    public int getCost() {
        return this.cost;
    }

    public String getDescription() {
        return this.name;
    }

    public Map<Ingredient, Integer> getIngredients() {
        return this.ingredients;
    }

}
