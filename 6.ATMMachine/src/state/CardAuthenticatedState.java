package state;

import cor.ATMMenuOption;
import entity.ATM;
import entity.Card;

public class CardAuthenticatedState extends ATMState {

    public CardAuthenticatedState(ATM atm) {
        super(atm);
    }

    @Override
    public void insertCard(Card card) {

        // invalid operation
        System.out.println("You already have inserted the card !!!");

    }

    @Override
    public void enterPin(String pin) {

        // invalid operation
        System.out.println("You already have entered the PIN !!!");

    }

    @Override
    public void selectOption(ATMMenuOption option) {

        // set ATM state to 'ProcessingState' and then
        // initiate the process
        atm.setNextState(new ProcessingState(atm));
        option.process();
        // once process is complete
        // return card
        System.out.println("Please collect your card !!!");
        atm.setCardReceived(null);
        // transition ATM back to the 'IDLE' state again
        atm.setNextState(new IdleState(atm));

    }

    @Override
    public void cancel() {

    }

}
