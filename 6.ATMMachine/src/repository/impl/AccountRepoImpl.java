package repository.impl;

import entity.Account;
import repository.AccountRepository;

import java.util.HashMap;
import java.util.Map;

public class AccountRepoImpl implements AccountRepository {

    // IN-MEMORY data storage
    private final Map<String, Account> accounts;

    public AccountRepoImpl() {
        accounts = new HashMap<>();
    }

    @Override
    public void createAccount(String accNo, String accHolderName) {

        Account account = new Account(accNo, accHolderName);
        account.setBalance(500);
        accounts.put(accNo, account);

    }

    @Override
    public Account getAccount(String accNo) {
        return accounts.get(accNo);
    }

    @Override
    public void updateAccount(Account account) {

        // update BALANCE
        accounts.put(account.getAccNo(), account);

    }

}
