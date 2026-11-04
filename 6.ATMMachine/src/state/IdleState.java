package state;

import cor.ATMMenuOption;
import entity.ATM;
import entity.Card;
import repository.CardRepository;

public class IdleState extends ATMState {

    public IdleState(ATM atm) {
        super(atm);
    }

    @Override
    public void insertCard(Card card) {

        System.out.println("Card is inserted...");
        atm.setCardReceived(card);

        // transition to 'HasCardState' state
        atm.setNextState(new HasCardState(atm));

    }

    @Override
    public void enterPin(String pin) {

        // invalid operation
        System.out.println("Please insert card first !!!");

    }

    @Override
    public void selectOption(ATMMenuOption option) {

        // invalid operation
        System.out.println("Please insert card first !!!");

    }

    @Override
    public void cancel() {

        // invalid operation
        System.out.println("No process has started yet !!!");

    }


}
