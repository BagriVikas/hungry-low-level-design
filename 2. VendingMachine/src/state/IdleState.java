package state;

import enums.Coin;
import machine.VendingMachine;

public class IdleState extends VendingMachineState {

    public IdleState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertCoin(Coin coin) {

        // Invalid operation
        System.out.println("No item is selected yet");

    }

    @Override
    public void selectItem(String itemId) {

        // valid operation
        boolean isItemAvailable = vendingMachine.isItemAvailable(itemId);
        if (isItemAvailable) {
            vendingMachine.setSelectedItemId(itemId);
            // transition to ITEM_SELECTED_STATE
            vendingMachine.setState(new ItemSelectedState(vendingMachine));
        } else {
            System.out.println("Item not available");
        }

    }

    @Override
    public void dispenseItem() {
        // invalid operation
        System.out.println("No item is selected yet");
    }

    @Override
    public void refund() {
        // invalid operation
        System.out.println("No coin is inserted yet");
    }

}
