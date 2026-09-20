package state;

import enums.CoffeeType;
import enums.CondimentType;

import java.util.List;

public class HasMoneyMachineState extends MachineState {

    public HasMoneyMachineState(CoffeeVendingMachine coffeeVendingMachine) {
        super(coffeeVendingMachine);
    }

    @Override
    public void selectCoffee(CoffeeType coffeeType, List<CondimentType> condiments) {
        // invalid operation
        System.out.println("You have selected " + this.getCoffeeVendingMachine().getSelectedCoffee().getDescription() + " already!!!");
    }

    @Override
    public void insertCoin(int coin) {
        // invalid operation
        System.out.println("You have already inserted the money");
    }

    @Override
    public void prepareCoffee() {

        // valid operation
        // start preparing the coffee
        // but before starting the process
        // first transition to the next state
        this.getCoffeeVendingMachine().setNextState(new PreparingAndDispensingCoffeeMachineState(this.getCoffeeVendingMachine()));
        // start preparing...
        this.getCoffeeVendingMachine().getSelectedCoffee().prepare();
        // once preparation is done
        // update stock in inventory
        this.getCoffeeVendingMachine().removeStock(this.getCoffeeVendingMachine().getSelectedCoffee().getIngredients());
        // refund money if required
        if (this.getCoffeeVendingMachine().getMoneyReceived() > this.getCoffeeVendingMachine().getSelectedCoffee().getCost()) {
            // refund
            System.out.println("Refunding money: " + (this.getCoffeeVendingMachine().getMoneyReceived() - this.getCoffeeVendingMachine().getSelectedCoffee().getCost()));
        }
        // reset the machine
        this.getCoffeeVendingMachine().reset();

    }
}
