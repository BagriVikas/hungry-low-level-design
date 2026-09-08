package state;

import machine.CoffeeMachine;

public class CoffeeSelectedMachineState extends MachineState{

    public CoffeeSelectedMachineState(CoffeeMachine coffeeMachine) {
        super(coffeeMachine);
    }

    @Override
    public void selectCoffee(String name) {

        // invalid operation
        System.out.println("You have already selected a coffee!!!");

    }

    @Override
    public void insertCoin(int amount) {

        // valid operation
        // check whether coffee can be bough using the 'amount' received
        if (coffeeMachine.getSelectedCoffee().getPrice() > amount) {
            System.out.println("Please insert enough coins");
            return;
        }
        coffeeMachine.setMoneyReceived(amount);
        // move to next state
        coffeeMachine.setState(new HasMoneyMachineState(coffeeMachine));

    }

    @Override
    public void refund() {

        // invalid operation
        System.out.println("You have not inserted any coin yet!!!");

    }

    @Override
    public void dispense() {

        // invalid operation
        System.out.println("You have not inserted any coin yet!!!");

    }

}
