package cor.withdrawal.dispenser;

import entity.ATM;

// Here COR is working...
public abstract class CashDispenser {

    protected ATM atm;

    protected CashDispenser nextCashDispenser;

    public CashDispenser(ATM atm) {
        this.atm = atm;
    }

    public abstract boolean canDispense(int amount);

    public void setNextCashDispenser(CashDispenser dispenser) {
        this.nextCashDispenser = dispenser;
    }

    public CashDispenser getNextCashDispenser() {
        return nextCashDispenser;
    }

}
