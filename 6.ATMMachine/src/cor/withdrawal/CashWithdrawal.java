package cor.withdrawal;

import cor.ATMMenuOption;
import cor.withdrawal.dispenser.CashDispenser;
import entity.ATM;

import java.util.Scanner;

public class CashWithdrawal implements ATMMenuOption {

    private ATM atm;

    private CashDispenser cashDispenser;

    private final static Scanner scanner = new Scanner(System.in);

    public CashWithdrawal(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void process() {

        // TODO: process cash-withdrawal request
        System.out.println("Enter amount");
        int amountToWithdraw = scanner.nextInt();

        // check whether user has enough balance to withdraw the entered amount
        int avlAccBal = atm.getCardReceived().getAccount().getBalance();
        if (avlAccBal < amountToWithdraw) {
            System.out.println("Not enough balance !!!");
            return;
        }
        boolean canDispense = cashDispenser.canDispense(amountToWithdraw);
        if (canDispense) {
            System.out.println("Dispensing...");
            // update ATM state (i.e. balance and the count for each denomination)
            // TODO: Don't update the 'ATM & ACCOUNT' state directly
            //  instead use the repository to update the state
        } else {
            System.out.println("Insufficient balance/denominations !!!");
        }

    }

    public ATM getAtm() {
        return atm;
    }

    public void setAtm(ATM atm) {
        this.atm = atm;
    }

    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    public void setCashDispenser(CashDispenser cashDispenser) {
        this.cashDispenser = cashDispenser;
    }
}
