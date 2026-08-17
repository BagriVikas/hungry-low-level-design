package entity;

import enums.MoneyType;

import java.util.concurrent.atomic.AtomicInteger;

public class Money {

    private final int value;
    private final MoneyType type;
    private final AtomicInteger quantity;

    public Money(int value, MoneyType type, int quantity) {
        this.value = value;
        this.type = type;
        this.quantity = new AtomicInteger(quantity);
    }

    public int getValue() {
        return value;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public MoneyType getType() {
        return type;
    }

    public void updateQuantity(int quantity) {
        this.quantity.addAndGet(quantity);
    }

}
