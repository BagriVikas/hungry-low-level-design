import cor.ATMMenuOption;
import entity.ATM;
import entity.Account;
import entity.Card;
import enums.MenuOption;
import factory.ATMMenuOptionFactory;

public class ATMDemo {

    public static void main(String[] args) {

        // create ACCOUNT
        Account acc = ATMClient.createAccount("Vikas");

        // create CARD
        Card card = ATMClient.createCard("6708", acc);

        // create ATM
        ATM atm = ATMClient.createATM();

        // perform one full txn
        // Step I: insert card
        atm.insertCard(card);

        // Step II: enter pin
        atm.enterPin("6708");

        // Step III: select option
        ATMMenuOption cashWithdrawal = ATMMenuOptionFactory.getATMMenuOption(
                MenuOption.CASH_WITHDRAWAL,
                atm
        );
        atm.selectOption(cashWithdrawal);

    }

}
