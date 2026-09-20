package state;

import enums.CoffeeType;
import enums.CondimentType;

import java.util.List;

public class SelectedCoffeeMachineState extends MachineState {

    public SelectedCoffeeMachineState(CoffeeVendingMachine coffeeVendingMachine) {
        super(coffeeVendingMachine);
    }

    @Override
    public void selectCoffee(CoffeeType coffeeType, List<CondimentType> condiments) {
        // invalid operation
        System.out.println("You have selected " + this.getCoffeeVendingMachine().getSelectedCoffee().getDescription() + " already!!!");
    }

    @Override
    public void insertCoin(int coin) {

        // valid operation
        // check whether received enough money
        if (coin < this.getCoffeeVendingMachine().getSelectedCoffee().getCost()) {
            System.out.println("Not enough money!!! Try inserting more next time...");
            // refund money received
            System.out.println("Refunding money: " + coin);
            // set machine to initial state
            this.getCoffeeVendingMachine().reset();
            return;
        }
        // has enough money
        // proceed ahead
        this.getCoffeeVendingMachine().setMoneyReceived(coin);
        // transition to the next state
        this.getCoffeeVendingMachine().setNextState(new HasMoneyMachineState(this.getCoffeeVendingMachine()));

    }

    @Override
    public void prepareCoffee() {
        // invalid operation
        System.out.println("Please insert a coin first !!!");
    }
}
