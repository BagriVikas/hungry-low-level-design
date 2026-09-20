package template;

import enums.Ingredient;

import java.util.Map;

public class Espresso extends Coffee {

    public Espresso() {
        super(
                "Espresso",
                12,
                Map.of(
                        Ingredient.WATER, 1,
                        Ingredient.MILK, 3,
                        Ingredient.COFFEE_BEANS, 6
                )
        );
    }

    @Override
    public void brewCoffee() {
        System.out.println("Brewing Espresso");
    }

}
