package state;

import enums.CoffeeType;
import enums.CondimentType;

import java.util.List;

public class PreparingAndDispensingCoffeeMachineState extends MachineState {

    public PreparingAndDispensingCoffeeMachineState(CoffeeVendingMachine coffeeVendingMachine) {
        super(coffeeVendingMachine);
    }

    @Override
    public void selectCoffee(CoffeeType coffeeType, List<CondimentType> condimentTypes) {
        // invalid operation
        System.out.println("Currently preparing your coffee... Cannot select coffee !!!");
    }

    @Override
    public void insertCoin(int coin) {
        // invalid operation
        System.out.println("Currently preparing your coffee... Cannot insert coin !!!");
    }

    @Override
    public void prepareCoffee() {
        // invalid operation
        System.out.println("Your coffee is already under preparation...");
    }
}
