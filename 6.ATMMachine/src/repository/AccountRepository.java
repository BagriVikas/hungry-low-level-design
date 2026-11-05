package repository;

import entity.Account;

import java.util.HashMap;
import java.util.Map;

// SINGLETON: IN-MEMORY data storage
public class AccountRepository {

    private static final AccountRepository accountRepository = new AccountRepository();
    private final Map<String, Account> accounts;

    private AccountRepository () {
        accounts = new HashMap<>();
    }

    public static AccountRepository getInstance() {
        return accountRepository;
    }

    public Account createAccount(String accNo, String accHolderName) {

        Account account = new Account(accNo, accHolderName);
        account.setBalance(500);
        accounts.put(accNo, account);
        return account;

    }

    public Account getAccount(String accNo) {
        return accounts.get(accNo);
    }

    public void updateAccount(Account account) {

        // update BALANCE
        accounts.put(account.getAccNo(), account);

    }

}
