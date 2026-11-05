package factory;

import cor.ATMMenuOption;
import cor.withdrawal.CashWithdrawal;
import cor.withdrawal.dispenser.Notes100Dispenser;
import cor.withdrawal.dispenser.Notes2000Dispenser;
import cor.withdrawal.dispenser.Notes500Dispenser;
import entity.ATM;
import enums.MenuOption;

public class ATMMenuOptionFactory {

    public static ATMMenuOption getATMMenuOption(MenuOption option, ATM atm) {

        ATMMenuOption atmMenuOption = null;
        if (MenuOption.CASH_WITHDRAWAL.equals(option)) {

            // not sure whether others follow COR or not
            // but for CASH_WITHDRAWAL we are using
            CashWithdrawal cashWithdrawal = new CashWithdrawal(atm);
            // setup COR structure for cash dispensers
            Notes100Dispenser dispense100 = new Notes100Dispenser(atm);
            Notes500Dispenser dispense500 = new Notes500Dispenser(atm);
            Notes2000Dispenser dispense2000 = new Notes2000Dispenser(atm);
            // link dispensers in a LL format
            cashWithdrawal.setCashDispenser(dispense2000);
            dispense2000.setNextCashDispenser(dispense500);
            dispense500.setNextCashDispenser(dispense100);
            atmMenuOption = cashWithdrawal;

        }
        return atmMenuOption;

    }

}
