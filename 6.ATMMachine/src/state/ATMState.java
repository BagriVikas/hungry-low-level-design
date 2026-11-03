package state;

import cor.ATMMenuOption;
import entity.ATM;
import entity.Card;

public abstract class ATMState {

    private ATM atm;

    public ATMState(ATM atm) {
        this.atm = atm;
    }

    public abstract void insertCard(Card card);

    public abstract void enterPin(String pin);

    public abstract void selectOption(ATMMenuOption option);

    public abstract void cancel();

}
