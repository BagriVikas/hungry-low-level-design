package state;

import machine.CoffeeMachine;

public class HasMoneyMachineState extends MachineState {

    public HasMoneyMachineState(CoffeeMachine coffeeMachine) {
        super(coffeeMachine);
    }

    @Override
    public void selectCoffee(String name) {

        // invalid operation
        System.out.println("You have already selected a coffee!!!");

    }

    @Override
    public void insertCoin(int amount) {

        // invalid operation
        System.out.println("You have already inserted a coin");

    }

    @Override
    public void refund() {

        // valid operation
        System.out.println("Returning money: " + coffeeMachine.getMoneyReceived());
        coffeeMachine.setState(new CoffeeSelectedMachineState(coffeeMachine));

    }

    @Override
    public void dispense() {

        // valid operation
        // move to next state
        coffeeMachine.setState(new DispensingMachineState(coffeeMachine));
        System.out.println("Dispensing... " + coffeeMachine.getSelectedCoffee().getName());
        // once dispensed refund money if required
        if (coffeeMachine.getMoneyReceived() > coffeeMachine.getSelectedCoffee().getPrice()) {
            System.out.println("Returning money: " + (coffeeMachine.getMoneyReceived() - coffeeMachine.getSelectedCoffee().getPrice()));
        }
        coffeeMachine.reset();

    }

}
