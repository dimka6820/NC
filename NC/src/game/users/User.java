package game.users;

import game.decks.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ִלטענטי on 26.10.2015.
 */
public abstract class User {
    protected String name;
    protected List<Card> cards;
    
    public User() {
        this.name = "user";
        this.cards = new ArrayList<Card>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int countPoints() {
        int countPoints = 0;
        for (Card temp : cards) countPoints += temp.getCount();
        return countPoints;
    }

    public List<String> returnNameCards() {
        List<String> cardsName = new ArrayList<>();
        int i = 0;
        for (Card temp : cards)
        {
            cardsName.add(temp.getName());
        }
        return cardsName;
    }

    public String toString() {
        return "name='" + name + '\'' +
                ", cards=" + returnNameCards().toString() + ", countPoints=" + countPoints() + "\n";
    }
}
