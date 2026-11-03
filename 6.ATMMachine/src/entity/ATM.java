package entity;

import cor.ATMMenuOption;
import state.ATMState;

public class ATM {

    private String id;
    private ATMState state;
    private Card cardReceived;
    private int balance;

    private int notesOf100;
    private int notesOf500;
    private int notesOf2000;

    public ATM(String id) {
        this.id = id;
    }

    public void insertCard(Card card) {
        state.insertCard(card);
    }

    public void enterPin(String pin) {
        state.enterPin(pin);
    }

    public void selectOption(ATMMenuOption option) {
        state.selectOption(option);
    }

    public void cancel() {
        state.cancel();
    }

    public void setNextState(ATMState state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Card getCardReceived() {
        return cardReceived;
    }

    public void setCardReceived(Card cardReceived) {
        this.cardReceived = cardReceived;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getNotesOf100() {
        return notesOf100;
    }

    public void setNotesOf100(int notesOf100) {
        this.notesOf100 = notesOf100;
    }

    public int getNotesOf500() {
        return notesOf500;
    }

    public void setNotesOf500(int notesOf500) {
        this.notesOf500 = notesOf500;
    }

    public int getNotesOf2000() {
        return notesOf2000;
    }

    public void setNotesOf2000(int notesOf2000) {
        this.notesOf2000 = notesOf2000;
    }
}
