package decorator;

import enums.Ingredient;
import template.Coffee;

import java.util.Map;

public class SugarSyrup extends Condiment {

    public SugarSyrup(Coffee coffee) {
        super(
                "Sugar Syrup",
                2,
                Map.of(
                        Ingredient.WATER, 2,
                        Ingredient.SUGAR, 5
                ),
                coffee
        );
    }

}
