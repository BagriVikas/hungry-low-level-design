package repository;

import entity.ATM;

public interface ATMRepository {

    void createATM(String id);

    ATM getATM(String id);

    void updateATM(ATM atm);

}
