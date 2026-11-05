package repository;

import entity.ATM;

import java.util.HashMap;
import java.util.Map;

// SINGLETON: IN-MEMORY data storage
public class ATMRepository {

    private static final ATMRepository atmRepository = new ATMRepository();
    private final Map<String, ATM> atms;

    private ATMRepository() {
        atms = new HashMap<>();
    }

    public static ATMRepository getInstance() {
        return atmRepository;
    }

    public ATM createATM(String id) {

        ATM atm = new ATM(id);
        atm.setBalance(25300);
        atm.setNotesOf100(3);
        atm.setNotesOf500(10);
        atm.setNotesOf2000(10);
        atms.put(id, atm);
        return atm;

    }

    public ATM getATM(String id) {
        return atms.get(id);
    }

    public void updateATM(ATM atm) {

        // update BALANCE & NOTES count
        atms.put(atm.getId(), atm);

    }

}
