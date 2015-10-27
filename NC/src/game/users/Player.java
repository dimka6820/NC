package game.users;

import game.Status;
import game.decks.Card;

import java.util.Random;

/**
 * Created by ִלטענטי on 21.10.2015.
 */
public class Player extends User{
    private Status status;
    private int money;
    private int rate;

    public Player(int i) {
        name = "Player"+i;
        status = Status.GAME;
        money = new Random().nextInt(10);
        rate = 0;
    }

    public void addCard(Card card)
    {
        cards.add(card);
    }

    public void setRate(int rate) {
        if(rate > money) this.rate = money;
        else this.rate = rate;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
