package entity;

public class Card {

    private String cardNo;
    private String pin;
    private Account account;

    public Card(String cardNo, String pin, Account account) {
        this.cardNo = cardNo;
        this.pin = pin;
        this.account = account;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
