package model;

import java.io.Serializable;

/**
 * Created by Дмитрий on 16.11.2015.
 */
public class Move implements Serializable {
    private String from;
    private String to;

    public Move() {
        from = "";
        to = "";
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "from='" + from + '\'' +
                ", to='" + to + '\'';
    }
}
