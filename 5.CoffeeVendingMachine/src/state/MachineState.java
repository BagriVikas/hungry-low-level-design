package state;

import enums.CoffeeType;
import enums.CondimentType;
import template.Coffee;

import java.util.List;

public abstract class MachineState {

    private CoffeeVendingMachine coffeeVendingMachine;

    public MachineState(CoffeeVendingMachine coffeeVendingMachine) {
        this.coffeeVendingMachine = coffeeVendingMachine;
    }

    public abstract void selectCoffee(CoffeeType coffeeType, List<CondimentType> condiments);

    public abstract void insertCoin(int coin);

    public abstract void prepareCoffee();

    public CoffeeVendingMachine getCoffeeVendingMachine() {
        return this.coffeeVendingMachine;
    }

}
