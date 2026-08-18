package machine;

import entity.Inventory;
import entity.Item;
import enums.Coin;
import state.IdleState;
import state.VendingMachineState;

public class VendingMachine {

    private static VendingMachine vendingMachine;
    private final Inventory inventory;
    private int balanceAmount;
    private String selectedItemId;
    private VendingMachineState state;

    private VendingMachine() {
        inventory = new Inventory();
        state = new IdleState(this);
        balanceAmount = 0;
    }

    public synchronized static VendingMachine getInstance() {

        if (null == vendingMachine) {
            vendingMachine = new VendingMachine();
        }
        return vendingMachine;

    }

    public void setState(VendingMachineState newState) {
        state = newState;
    }

    public void reset() {
        balanceAmount = 0;
        selectedItemId = null;
        state = new IdleState(this);
    }

    public void insertCoin(Coin coin) {
        // delegating to state
        state.insertCoin(coin);
    }

    public void selectItem(String itemId) {
        // delegating to state
        state.selectItem(itemId);
    }

    public void dispenseItem() {
        // delegating to state
        state.dispenseItem();
    }

    public void refund() {
        // delegating to state
        state.refund();
    }

    public void returnMoneyToUser() {
        System.out.println("Please collect your money: " + balanceAmount);
        balanceAmount = 0;
    }

    public Item addItem(String name, int price) {
        return inventory.addItem(name, price);
    }

    public void addStockForItem(String itemId, int quantity) {
        inventory.addStockForItem(itemId, quantity);
    }

    public boolean isItemAvailable(String itemId) {
        return inventory.isItemAvailable(itemId);
    }

    public Item getItem(String itemId) {
        return inventory.getItem(itemId);
    }

    public void dispenseItem(String itemId) {
        inventory.dispenseItem(itemId);
    }

    public void setBalanceAmount(int amount) {
        balanceAmount = amount;
    }

    public void setSelectedItemId(String itemId) {
        selectedItemId = itemId;
    }

    public int getBalanceAmount() {
        return balanceAmount;
    }

    public String getSelectedItemId() {
        return selectedItemId;
    }

}
