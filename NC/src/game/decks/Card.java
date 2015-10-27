package game.decks;

/**
 * Created by ִלטענטי on 26.10.2015.
 */
public class Card {
    private boolean status;
    private int count;
    private String name;

    public Card(int count, String name) {
        status = false;
        this.count = count;
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
