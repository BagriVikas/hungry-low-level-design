package repository;

import entity.Account;
import entity.Card;

import java.util.Map;

public interface CardRepository {

    void createCard(String cardNo, String cardPin, Account account);

    Card getCard(String cardNo);

}
