package entity;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Product {

    private final String id;
    private final String name;
    private final int price;
    private final AtomicInteger quantity;

    public Product(String name, int price, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.quantity = new AtomicInteger(quantity);
    }

    public String getId() {
        return id;
    }

    public String getName() {return name;}

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void updateQuantity(int quantity) {
        this.quantity.addAndGet(quantity);
    }

}
