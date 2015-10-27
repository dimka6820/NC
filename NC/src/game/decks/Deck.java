package game.decks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ִלטענטי on 26.10.2015.
 */
public abstract class Deck {
    public List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }
}
