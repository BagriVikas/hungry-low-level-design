package state;

import machine.CoffeeMachine;

public class DispensingMachineState extends MachineState {

    public DispensingMachineState(CoffeeMachine coffeeMachine) {
        super(coffeeMachine);
    }

    @Override
    public void selectCoffee(String name) {

        // invalid operation
        System.out.println("Currently dispensing !!! Please wait...");

    }

    @Override
    public void insertCoin(int amount) {

        // invalid operation
        System.out.println("Currently dispensing !!! Please wait...");

    }

    @Override
    public void refund() {

        // invalid operation
        System.out.println("You have not inserted any coin yet!!!");

    }

    @Override
    public void dispense() {

        // invalid operation
        System.out.println("Currently dispensing !!! Please wait...");

    }

}
