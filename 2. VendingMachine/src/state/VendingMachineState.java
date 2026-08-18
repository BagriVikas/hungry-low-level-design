package state;

import enums.Coin;
import machine.VendingMachine;

public abstract class VendingMachineState {

    // context for states as per 'State' design pattern
    VendingMachine vendingMachine;

    VendingMachineState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public abstract void insertCoin(Coin coin);

    public abstract void selectItem(String itemId);

    public abstract void dispenseItem();

    public abstract void refund();

}
