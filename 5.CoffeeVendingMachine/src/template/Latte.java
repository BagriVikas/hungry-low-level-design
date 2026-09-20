package template;

import enums.Ingredient;

import java.util.Map;

public class Latte extends Coffee {

    public Latte() {
        super(
                "Latte",
                11,
                Map.of(
                        Ingredient.WATER, 4,
                        Ingredient.COFFEE_BEANS, 8,
                        Ingredient.MILK, 5
                )
        );
    }

    @Override
    public void brewCoffee() {
        System.out.println("Brewing Latte...");
    }

}
