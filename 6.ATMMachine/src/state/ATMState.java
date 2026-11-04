package state;

import cor.ATMMenuOption;
import entity.ATM;
import entity.Card;
import repository.CardRepository;
import repository.impl.CardRepoImpl;

public abstract class ATMState {

    protected ATM atm;

    protected CardRepository cardRepository;

    public ATMState(ATM atm) {
        this.atm = atm;
        this.cardRepository = new CardRepoImpl();
    }

    public abstract void insertCard(Card card);

    public abstract void enterPin(String pin);

    public abstract void selectOption(ATMMenuOption option);

    public abstract void cancel();

}
