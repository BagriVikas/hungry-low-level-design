package state;

import machine.CoffeeMachine;

public abstract class MachineState {

    protected CoffeeMachine coffeeMachine;

    public MachineState(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    public abstract void selectCoffee(String name);

    public abstract void insertCoin(int amount);

    public abstract void refund();

    public abstract void dispense();

}
