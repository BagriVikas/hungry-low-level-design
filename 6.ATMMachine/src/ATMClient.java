import entity.ATM;
import entity.Account;
import entity.Card;
import repository.ATMRepository;
import repository.AccountRepository;
import repository.CardRepository;

import java.util.UUID;

public class ATMClient {

    private static final AccountRepository accRepo = AccountRepository.getInstance();
    private static final CardRepository cardRepo = CardRepository.getInstance();
    private static final ATMRepository atmRepo = ATMRepository.getInstance();

    // some methods to perform testing
    public static Account createAccount(String accountHolderName) {
        return accRepo.createAccount("XXX-" + accountHolderName + "-123", accountHolderName);
    }

    public static Card createCard(String pin, Account account) {
        return cardRepo.createCard("CCC-" + pin, pin, account);
    }

    public static ATM createATM() {
        return atmRepo.createATM(UUID.randomUUID().toString());
    }

}
