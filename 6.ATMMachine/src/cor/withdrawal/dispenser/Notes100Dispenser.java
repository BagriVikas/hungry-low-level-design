package cor.withdrawal.dispenser;

import entity.ATM;

public class Notes100Dispenser extends CashDispenser {

    public Notes100Dispenser(ATM atm) {
        super(atm);
    }

    @Override
    public boolean canDispense(int amount) {

        // some 'amount' has to be dispensed
        // check whether ATM has enough credits
        int atmBal = atm.getBalance();
        if (atmBal < amount) {
            if (null != nextCashDispenser) {
                return nextCashDispenser.canDispense(amount);
            } else {
                return false;
            }
        }

        // dispense notes for the denomination 100
        int notesAvl = atm.getNotesOf100();
        int notesReq = amount / 100;
        int notesDispense  = Math.min(notesAvl, notesReq);
        int amtDispense = notesDispense * 100;
        amount -= amtDispense;

        // update state for ATM
        atm.setNotesOf100(notesAvl - notesDispense);
        atm.setBalance(atmBal - amtDispense);

        // check whether txn can be completed
        if (amount == 0) {
            return true;
        }
        if (null == nextCashDispenser) {
            return false;
        }

        // since whole amount is not fetched completely yet
        // try checking the same for the next cash dispenser
        return canDispense(amount);

    }
}
