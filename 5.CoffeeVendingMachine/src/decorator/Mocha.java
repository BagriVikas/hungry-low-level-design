package decorator;

import enums.Ingredient;
import template.Coffee;

import java.util.Map;

public class Mocha extends Condiment {

    public Mocha(Coffee coffee) {
        super(
                "Mocha",
                4,
                Map.of(
                        Ingredient.WATER, 2,
                        Ingredient.COFFEE_BEANS, 5
                ),
                coffee
        );
    }

}
