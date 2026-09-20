package factory;

import decorator.Condiment;
import decorator.Mocha;
import decorator.SugarSyrup;
import enums.CondimentType;
import template.Coffee;

public class CondimentFactory {

    public static Condiment getCondiment(CondimentType condimentType, Coffee coffee) {

        Condiment condiment = null;
        if (CondimentType.SUGAR_SYRUP.equals(condimentType)) {
            condiment = new SugarSyrup(coffee);
        }
        if (CondimentType.MOCHA.equals(condimentType)) {
            condiment = new Mocha(coffee);
        }
        return condiment;

    }

}
