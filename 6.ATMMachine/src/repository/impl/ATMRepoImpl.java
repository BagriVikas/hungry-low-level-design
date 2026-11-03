package repository.impl;

import entity.ATM;
import repository.ATMRepository;

import java.util.HashMap;
import java.util.Map;

public class ATMRepoImpl implements ATMRepository {

    private final Map<String, ATM> atms;

    public ATMRepoImpl() {
        atms = new HashMap<>();
    }

    @Override
    public void createATM(String id) {

        ATM atm = new ATM(id);
        atm.setBalance(2000);
        atms.put(id, atm);

    }

    @Override
    public ATM getATM(String id) {
        return atms.get(id);
    }

    @Override
    public void updateATM(ATM atm) {

        // update BALANCE & NOTES count
        atms.put(atm.getId(), atm);

    }

}
