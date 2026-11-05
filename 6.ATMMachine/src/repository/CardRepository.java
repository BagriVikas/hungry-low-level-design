package repository;

import entity.Account;
import entity.Card;

import java.util.HashMap;
import java.util.Map;

// SINGLETON: IN-MEMORY data storage
public class CardRepository {

    private static final CardRepository cardRepository = new CardRepository();
    private final Map<String, Card> cards;

    private CardRepository() {
        cards = new HashMap<>();
    }

    public static CardRepository getInstance() {
        return cardRepository;
    }

    public Card createCard(String cardNo, String cardPin, Account account) {

        Card card = new Card(cardNo, cardPin, account);
        cards.put(cardNo, card);
        return card;

    }

    public Card getCard(String cardNo) {
        return cards.get(cardNo);
    }

}
