package state;

import decorator.Condiment;
import enums.CoffeeType;
import enums.CondimentType;
import factory.CoffeeFactory;
import factory.CondimentFactory;
import template.Coffee;

import java.util.List;

public class IdleMachineState extends MachineState {

    public IdleMachineState(CoffeeVendingMachine coffeeVendingMachine) {
        super(coffeeVendingMachine);
    }

    @Override
    public void selectCoffee(CoffeeType coffeeType, List<CondimentType> condiments) {

        // valid operation
        Coffee coffee = CoffeeFactory.getCoffee(coffeeType);
        // apply all decorators or in other words
        // add all required condiments
        for (CondimentType condimentType: condiments) {
            coffee = CondimentFactory.getCondiment(condimentType, coffee);
        }
        // check for all the required ingredients for both
        // the base coffee and the added condiments
        boolean hasEnoughStock = this.getCoffeeVendingMachine().hasEnoughStock(coffee.getIngredients());
        if (!hasEnoughStock) {
            System.out.println("Sorry!!! Currently all required ingredients are not available...");
            return;
        }
        // has enough ingredients for coffee
        this.getCoffeeVendingMachine().setSelectedCoffee(coffee);
        // transition to the next state
        this.getCoffeeVendingMachine().setNextState(new SelectedCoffeeMachineState(this.getCoffeeVendingMachine()));

    }

    @Override
    public void insertCoin(int coin) {
        // invalid operation
        System.out.println("Please select a coffee first...");
    }

    @Override
    public void prepareCoffee() {
        // invalid operation
        System.out.println("Please select a coffee first...");
    }
}
