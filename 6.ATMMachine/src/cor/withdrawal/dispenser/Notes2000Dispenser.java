package cor.withdrawal.dispenser;

import entity.ATM;

public class Notes2000Dispenser extends CashDispenser {

    public Notes2000Dispenser(ATM atm) {
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

        // dispense notes for the denomination 2000
        int notesAvl = atm.getNotesOf2000();
        int notesReq = amount / 2000;
        int notesDispense  = Math.min(notesAvl, notesReq);
        int amtDispense = notesDispense * 2000;
        amount -= amtDispense;

        // update state for ATM
        atm.setNotesOf2000(notesAvl - notesDispense);
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
        return nextCashDispenser.canDispense(amount);

    }

}
