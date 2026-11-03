package state;

import cor.ATMMenuOption;
import entity.ATM;
import entity.Card;

public class ProcessingState extends ATMState {

    public ProcessingState(ATM atm) {
        super(atm);
    }

    @Override
    public void insertCard(Card card) {

    }

    @Override
    public void enterPin(String pin) {

    }

    @Override
    public void selectOption(ATMMenuOption option) {

    }

    @Override
    public void cancel() {

    }

}
