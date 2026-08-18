package enums;

public enum Coin {

    ONE_RUPEE_COIN(1),
    TWO_RUPEE_COIN(2),
    FIVE_RUPEE_COIN(5),
    TEN_RUPEE_COIN(10);

    private final int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
