package factory;

import enums.CoffeeType;
import template.Cappuccino;
import template.Coffee;
import template.Espresso;
import template.Latte;

public class CoffeeFactory {

    public static Coffee getCoffee(CoffeeType coffeeType) {

        Coffee coffee = null;
        if (CoffeeType.CAPPUCCINO.equals(coffeeType)) {
            coffee = new Cappuccino();
        }
        if (CoffeeType.LATTE.equals(coffeeType)) {
            coffee = new Latte();
        }
        if (CoffeeType.ESPRESSO.equals(coffeeType)) {
            coffee = new Espresso();
        }
        return coffee;

    }

}
