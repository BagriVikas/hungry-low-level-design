package repository.impl;

import entity.Account;
import entity.Card;
import repository.CardRepository;

import java.util.HashMap;
import java.util.Map;

// IN-MEMORY data storage
public class CardRepoImpl implements CardRepository {

    private final Map<String, Card> cards;

    public CardRepoImpl() {
        cards = new HashMap<>();
    }

    @Override
    public void createCard(String cardNo, String cardPin, Account account) {

        Card card = new Card(cardNo, cardPin, account);
        cards.put(cardNo, card);

    }

    @Override
    public Card getCard(String cardNo) {
        return cards.get(cardNo);
    }

}
