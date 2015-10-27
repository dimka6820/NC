package game.users;

import game.decks.Card;
import game.decks.Dec36;
import game.decks.Deck;

import java.util.Random;

/**
 * Created by ִלטענטי on 21.10.2015.
 */
public class Dealer extends User {
    private Random random = new Random();
    private Deck deck;

    public Dealer() {
        super();
        name = "Dealer";
        deck = new Dec36();
        cards.add(returnCard());
        cards.add(returnCard());
    }

    public Card returnCard() {
        while (true) {
            Card card = deck.getDeck().get(random.nextInt(35) + 1);
            if (card.isStatus() == false) {
                card.setStatus(true);
                return card;
            }
        }
    }
}
