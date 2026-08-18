package entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Inventory {

    private final Map<String, Item> items;
    private final Map<String, Integer> itemStock;

    public Inventory() {
        items = new HashMap<>();
        itemStock = new HashMap<>();
    }

    public Item addItem(String name, int price) {

        // just adding a new item
        // no stock is added for item yet
        Item item = new Item(UUID.randomUUID().toString(), name, price);
        items.put(item.getId(), item);
        itemStock.put(item.getId(), 0);
        return item;

    }

    public void addStockForItem(String itemId, int quantity) {

        Item item = items.get(itemId);
        if (null == item) {
            System.out.println("item not found");
        }
        itemStock.put(itemId, itemStock.get(itemId) + quantity);

    }

    public boolean isItemAvailable(String itemId) {
        return items.containsKey(itemId) && itemStock.get(itemId) > 0;
    }

    public Item getItem(String itemId) {
        return items.get(itemId);
    }

    public void dispenseItem(String itemId) {

        if (items.containsKey(itemId) && itemStock.get(itemId) > 0) {
            itemStock.put(itemId, itemStock.get(itemId) - 1);
        }

    }

}
