package state;

import cor.ATMMenuOption;
import entity.ATM;
import entity.Card;

public class HasCardState extends ATMState {

    public HasCardState(ATM atm) {
        super(atm);
    }

    @Override
    public void insertCard(Card card) {

        // invalid operation
        System.out.println("You already have inserted the card !!!");

    }

    @Override
    public void enterPin(String pin) {

        // check whether pin entered is correct or not
        String actualPin = cardRepository.getCard(atm.getCardReceived().getCardNo()).getPin();
        if (!actualPin.equals(pin)) {
            // invalid PIN
            System.out.println("Invalid PIN !!!");
            // abort the process
            System.out.println("Please collect your card !!!");
            atm.setCardReceived(null);
            // transition back to IDLE state
            atm.setNextState(new IdleState(atm));
            return;
        }

        // valid PIN
        System.out.println("PIN authenticated successfully");
        // transition to the next state 'CardAuthenticatedState'
        atm.setNextState(new CardAuthenticatedState(atm));

    }

    @Override
    public void selectOption(ATMMenuOption option) {

        // invalid operation
        System.out.println("Please enter PIN first !!!");

    }

    @Override
    public void cancel() {

        System.out.println("Cancelled !!!");
        System.out.println("Please collect your card !!!");
        atm.setCardReceived(null);
        // transition to 'IDLE' state
        atm.setNextState(new IdleState(atm));

    }

}
