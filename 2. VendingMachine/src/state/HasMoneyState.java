package state;

import entity.Item;
import enums.Coin;
import machine.VendingMachine;

public class HasMoneyState extends VendingMachineState {

    public HasMoneyState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertCoin(Coin coin) {
        // invalid operation
        System.out.println("Coin is already inserted");
    }

    @Override
    public void selectItem(String itemId) {
        // invalid operation
        System.out.println("Item is already selected");
    }

    @Override
    public void dispenseItem() {

        // valid operation
        // transition to DISPENSING_ITEM_STATE
        vendingMachine.setState(new DispensingItemState(vendingMachine));
        Item item = vendingMachine.getItem(vendingMachine.getSelectedItemId());
        System.out.println("Dispensing item: " + item.getName());
        vendingMachine.dispenseItem(vendingMachine.getSelectedItemId());
        vendingMachine.setBalanceAmount(vendingMachine.getBalanceAmount() - item.getPrice());
        vendingMachine.returnMoneyToUser();
        vendingMachine.reset();

    }

    @Override
    public void refund() {

        // valid operation
        vendingMachine.returnMoneyToUser();
        vendingMachine.reset();

    }

}
