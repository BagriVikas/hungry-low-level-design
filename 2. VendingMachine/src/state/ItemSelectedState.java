package state;

import entity.Item;
import enums.Coin;
import machine.VendingMachine;

public class ItemSelectedState extends VendingMachineState {

    public ItemSelectedState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }
    @Override
    public void insertCoin(Coin coin) {

        // valid operation
        int coinValue = coin.getValue();
        Item item = vendingMachine.getItem(vendingMachine.getSelectedItemId());
        if (coinValue >= item.getPrice()) {
            vendingMachine.setBalanceAmount(coinValue);
            // transition to HAS_MONEY_STATE
            vendingMachine.setState(new HasMoneyState(vendingMachine));
        } else {
            System.out.println("Not enough money");
            vendingMachine.returnMoneyToUser();
        }

    }

    @Override
    public void selectItem(String itemId) {
        // invalid operation
        System.out.println("Item is already selected");
    }

    @Override
    public void dispenseItem() {
        // invalid operation
        System.out.println("Coin is not inserted yet");
    }

    @Override
    public void refund() {
        // invalid operation
        System.out.println("Coin is not inserted yet");
    }

}
