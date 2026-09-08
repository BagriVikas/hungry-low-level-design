package entity;

import enums.MeasurementUnit;

public class Ingredient {

    private String name;
    private MeasurementUnit unit;
    private int quantity;

    public Ingredient(String name, MeasurementUnit unit) {
        this.name = name;
        this.quantity = 0;
        this.unit = unit;
    }

    public void addStocks(int quantity) {
        this.quantity += quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MeasurementUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasurementUnit unit) {
        this.unit = unit;
    }
}
