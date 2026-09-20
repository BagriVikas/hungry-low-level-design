package template;

import enums.Ingredient;

import java.util.Map;

public class Cappuccino extends Coffee {

    public Cappuccino() {
        super("Cappuccino",
                10,
                Map.of(
                        Ingredient.WATER, 2,
                        Ingredient.MILK, 2,
                        Ingredient.COFFEE_BEANS, 5
                )
        );
    }

    @Override
    public void brewCoffee() {
        System.out.println("Brewing Cappuccino...");
    }

}
