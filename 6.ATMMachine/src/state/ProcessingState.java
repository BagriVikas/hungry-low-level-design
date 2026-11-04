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

        // invalid operation
        System.out.println("Currently processing... Cannot insert a card !!!");

    }

    @Override
    public void enterPin(String pin) {

        // invalid operation
        System.out.println("Currently processing... Cannot enter a pin !!!");

    }

    @Override
    public void selectOption(ATMMenuOption option) {

        // invalid operation
        System.out.println("Currently processing... Cannot select an option !!!");

    }

    @Override
    public void cancel() {

        // invalid operation
        System.out.println("Currently processing... Cannot cancel !!!");

    }

}
