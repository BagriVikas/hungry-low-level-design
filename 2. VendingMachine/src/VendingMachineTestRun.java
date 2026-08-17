import entity.Money;
import entity.Product;
import enums.MoneyType;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineTestRun {

    public static void main(String[] args) {

        VendingMachineClient vendingMachineClient = new VendingMachineClient();
        Product lays = vendingMachineClient.addProduct("Lays", 5, 5);
        Money oneRupeeCoin = new Money(1, MoneyType.COIN, 5);
        Money twoRupeeCoin = new Money(2, MoneyType.COIN, 5);
        Money oneRupeeNote = new Money(1, MoneyType.NOTE, 5);
        Money twoRupeeNote = new Money(2, MoneyType.NOTE, 5);
        List<Money> coinsAndNotesStock = new ArrayList<>();
        coinsAndNotesStock.add(oneRupeeCoin);
        coinsAndNotesStock.add(twoRupeeCoin);
        coinsAndNotesStock.add(oneRupeeNote);
        coinsAndNotesStock.add(twoRupeeNote);
        vendingMachineClient.addMoneyStock(coinsAndNotesStock);
        Money tenRupeeNote = new Money(10, MoneyType.NOTE, 1);
        List<Money> coinsAndNotes = new ArrayList<>();
        coinsAndNotes.add(tenRupeeNote);
        List<Money> returnedMoney = vendingMachineClient.buyProduct(lays.getId(), coinsAndNotes);
        for (Money money: returnedMoney) {
            System.out.println(money.getQuantity() + " " + money.getType() +"s of " + money.getValue() + " rupee received");
        }

    }

}
