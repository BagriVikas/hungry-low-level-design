package repository;

import entity.Account;

public interface AccountRepository {

    void createAccount(String accNo, String accHolderName);

    Account getAccount(String accNo);

    void updateAccount(Account account);

}
