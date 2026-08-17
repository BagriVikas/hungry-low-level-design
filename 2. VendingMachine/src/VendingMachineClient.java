import entity.Money;
import entity.Product;
import enums.MoneyType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class VendingMachineClient {

    private final Map<String, Product> products = new ConcurrentHashMap<>();
    private final Map<Integer, Money> coinsAndCount = new ConcurrentHashMap<>();
    private final Map<Integer, Money> notesAndCount = new ConcurrentHashMap<>();

    public Product addProduct(String name, int price, int quantity) {

        Product product = new Product(name, price, quantity);
        products.put(product.getId(), product);
        return product;

    }

    public void addStockForProduct(String productId, int quantity) {

        Product product = products.get(productId);
        if (null == product) {
            System.out.println("Product with id " + productId + " not found");
            return;
        }
        product.updateQuantity(quantity);

    }

    public void addMoneyStock(List<Money> coinsAndNotes) {

        for (Money money: coinsAndNotes) {
            int value = money.getValue();
            MoneyType type = money.getType();
            int quantity = money.getQuantity();
            if (MoneyType.COIN.equals(type)) {
                if (!coinsAndCount.containsKey(value)) {
                    coinsAndCount.put(value, new Money(value, MoneyType.COIN, 0));
                }
                coinsAndCount.get(value).updateQuantity(quantity);
            } else {
                if (!notesAndCount.containsKey(value)) {
                    notesAndCount.put(value, new Money(value, MoneyType.NOTE, 0));
                }
                notesAndCount.get(value).updateQuantity(quantity);
            }
        }

    }

    public List<Money> buyProduct(String productId, List<Money> coinsAndNotes) {

        Product product = products.get(productId);
        if (null == product) {
            System.out.println("Product with id " + productId + " not found");
            return null;
        }
        if (product.getQuantity() < 1) {
            System.out.println("Product with id " + productId + " is out of stock");
            return null;
        }
        int amountPaid = 0;
        for (Money money: coinsAndNotes) {
            amountPaid += (money.getValue() * money.getQuantity());
        }
        if (amountPaid < product.getPrice()) {
            System.out.println("Insufficient funds!!!");
            return null;
        }
        int balanceAmount = amountPaid - product.getPrice();
        if (balanceAmount == 0) {
            product.updateQuantity(-1);
            System.out.println("Bought " + product.getName() + " for " + product.getPrice() + " rupees");
            return new ArrayList<>();
        }

        // let's check whether we have a correct combination of coins and notes
        // that can suffice for the 'balanceAmount' using the available denominations
        return getCoinsAndNotes(balanceAmount, coinsAndNotes);

    }

    private List<Money> getCoinsAndNotes(int balanceAmount, List<Money> coinsAndNotes) {

        // collect money inserted by user
        // bcz money submitted may contribute
        // in the amount to be returned back to the user
        addMoneyStock(coinsAndNotes);
        // TODO: try to come up with a possible correct combination of coins/notes
        //  check for all available denominations
        Set<Integer> denominationsSet = new TreeSet<>();
        denominationsSet.addAll(coinsAndCount.keySet());
        denominationsSet.addAll(notesAndCount.keySet());
        List<Integer> denominations = new ArrayList<>(denominationsSet);
        Map<Integer, Money> coins = new HashMap<>();
        Map<Integer, Money> notes = new HashMap<>();
        boolean coinsAndNotesCombinationFound = hasCoinsAndNotesCombination(0, balanceAmount, true, denominations, coins, notes);
        if (coinsAndNotesCombinationFound) {
            List<Money> returnMoney = new ArrayList<>();
            returnMoney.addAll(coins.values());
            returnMoney.addAll(notes.values());
            return returnMoney;
        } else {
            // TODO: throw exception -> Insufficient funds, cannot complete order
            //  return money inserted by the user
            removeMoneyFromStock(coinsAndNotes);
            return coinsAndNotes;
        }

    }

    private void removeMoneyFromStock(List<Money> coinsAndNotes) {

        List<Money> removeMoney = coinsAndNotes.stream()
                .map(money -> new Money(money.getValue(), money.getType(), -1 * money.getQuantity()))
                .collect(Collectors.toList());
        addMoneyStock(removeMoney);

    }

    private boolean hasCoinsAndNotesCombination(int i, int balanceAmount,boolean pickCoin,
                                                List<Integer> denominations,
                                                Map<Integer, Money> coins, Map<Integer, Money> notes) {

        if (balanceAmount == 0) {
            // required combination of coins and notes is found
            return true;
        }
        if (i == denominations.size()) {
            // no combination of coins and notes is found
            return false;
        }
        boolean combinationFound;
        if (pickCoin) {
            if (coinsAndCount.containsKey(denominations.get(i)) && coinsAndCount.get(denominations.get(i)).getQuantity() >= 1) {
                if (!coins.containsKey(denominations.get(i))) {
                    coins.put(denominations.get(i), new Money(denominations.get(i), MoneyType.COIN, 0));
                }
                coins.get(denominations.get(i)).updateQuantity(1);
                coinsAndCount.get(denominations.get(i)).updateQuantity(-1);
                combinationFound = hasCoinsAndNotesCombination(i, balanceAmount - denominations.get(i), pickCoin, denominations, coins, notes);
                if (!combinationFound) {
                    // release coin
                    coinsAndCount.get(denominations.get(i)).updateQuantity(1);
                    coins.get(denominations.get(i)).updateQuantity(-1);
                    if (coins.get(denominations.get(i)).getQuantity() == 0) {
                        coins.remove(denominations.get(i));
                    }
                }
            } else {
                combinationFound = hasCoinsAndNotesCombination(i, balanceAmount, false, denominations, coins, notes);
            }
        } else {
            if (notesAndCount.containsKey(denominations.get(i)) && notesAndCount.get(denominations.get(i)).getQuantity() >= 1) {
                if (!notes.containsKey(denominations.get(i))) {
                    notes.put(denominations.get(i), new Money(denominations.get(i), MoneyType.NOTE, 0));
                }
                notes.get(denominations.get(i)).updateQuantity(1);
                notesAndCount.get(denominations.get(i)).updateQuantity(-1);
                combinationFound = hasCoinsAndNotesCombination(i, balanceAmount - denominations.get(i), pickCoin, denominations, coins, notes);
                if (!combinationFound) {
                    // release note
                    notesAndCount.get(denominations.get(i)).updateQuantity(1);
                    notes.get(denominations.get(i)).updateQuantity(-1);
                    if (notes.get(denominations.get(i)).getQuantity() == 0) {
                        notes.remove(denominations.get(i));
                    }
                }
            } else {
                combinationFound = hasCoinsAndNotesCombination(i + 1, balanceAmount, true, denominations, coins, notes);
            }
        }
        return combinationFound;

    }

}
