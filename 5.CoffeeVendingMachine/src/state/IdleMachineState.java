package state;

import entity.Coffee;
import machine.CoffeeMachine;

public class IdleMachineState extends MachineState {

    public IdleMachineState(CoffeeMachine coffeeMachine) {
        super(coffeeMachine);
    }

    @Override
    public void selectCoffee(String name) {

        // valid operation
        Coffee coffee = coffeeMachine.getCoffee(name);
        if (coffee == null) {
            System.out.println("Currently, " + name + " is not available");
            return;
        }
        boolean hasEnoughIngredientsForCoffee = coffeeMachine.hasEnoughCoffeeIngredients(coffee);
        if (hasEnoughIngredientsForCoffee) {
            // proceed to the next stage
            coffeeMachine.setSelectedCoffee(coffee);
            coffeeMachine.setState(new CoffeeSelectedMachineState(coffeeMachine));
        } else {
            System.out.println("Currently, ingredients for " + name + " are not available");
        }

    }

    @Override
    public void insertCoin(int amount) {

        // invalid operation
        System.out.println("You have not selected any coffee yet!!!");

    }

    @Override
    public void refund() {

        // invalid operation
        System.out.println("You have not inserted any coin yet!!!");

    }

    @Override
    public void dispense() {

        // invalid operation
        System.out.println("You have not selected any coffee yet!!!");

    }
}
