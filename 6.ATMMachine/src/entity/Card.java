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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
