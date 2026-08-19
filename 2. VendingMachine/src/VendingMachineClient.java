import entity.Item;
import enums.Coin;
import machine.VendingMachine;

public class VendingMachineClient {

    public static void main(String[] args) {

        VendingMachine vendingMachine = VendingMachine.getInstance();

        // Add products to the inventory
        Item coke = vendingMachine.addItem("Coke", 25);
        vendingMachine.addStockForItem(coke.getId(), 3);
        Item pepsi = vendingMachine.addItem( "Pepsi", 7);
        vendingMachine.addStockForItem(pepsi.getId(), 2);
        Item water = vendingMachine.addItem( "Water", 10);
        vendingMachine.addStockForItem(water.getId(), 5);

        // Select a product
        System.out.println("\n--- Step 1: Select an item ---");
        vendingMachine.selectItem(coke.getId());

        // Insert coins
        System.out.println("\n--- Step 2: Insert coins ---");
        vendingMachine.insertCoin(Coin.TEN_RUPEE_COIN); // 10
        vendingMachine.insertCoin(Coin.TEN_RUPEE_COIN); // 10
        vendingMachine.insertCoin(Coin.FIVE_RUPEE_COIN); // 5

        // Dispense the product
        System.out.println("\n--- Step 3: Dispense item ---");
        vendingMachine.dispenseItem(); // Should dispense Coke

        // Select another item
        System.out.println("\n--- Step 4: Select another item ---");
        vendingMachine.selectItem(pepsi.getId());

        // Insert more amount
        System.out.println("\n--- Step 5: Insert more than needed ---");
        vendingMachine.insertCoin(Coin.TEN_RUPEE_COIN); // 10

        // Try to dispense the product
        System.out.println("\n--- Step 6: Dispense and return change ---");
        vendingMachine.dispenseItem();

    }

}
