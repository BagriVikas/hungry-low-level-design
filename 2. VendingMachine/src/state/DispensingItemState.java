package state;

import enums.Coin;
import machine.VendingMachine;

public class DispensingItemState extends VendingMachineState {

    public DispensingItemState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertCoin(Coin coin) {
        // invalid operation
        System.out.println("Please wait..., dispensing item !!!");
    }

    @Override
    public void selectItem(String itemId) {
        // invalid operation
        System.out.println("Please wait..., dispensing item !!!");
    }

    @Override
    public void dispenseItem() {
        // invalid operation
        System.out.println("Please wait..., dispensing item !!!");
    }

    @Override
    public void refund() {
        // invalid operation
        System.out.println("Please wait..., dispensing item !!!");
    }
}
