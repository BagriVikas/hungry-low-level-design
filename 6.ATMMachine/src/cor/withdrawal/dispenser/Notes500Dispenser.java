package cor.withdrawal.dispenser;

import entity.ATM;

public class Notes500Dispenser extends CashDispenser {

    public Notes500Dispenser(ATM atm) {
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

        // dispense notes for the denomination 500
        int notesAvl = atm.getNotesOf500();
        int notesReq = amount / 500;
        int notesDispense  = Math.min(notesAvl, notesReq);
        int amtDispense = notesDispense * 500;
        amount -= amtDispense;

        // update state for ATM
        atm.setNotesOf500(notesAvl - notesDispense);
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
