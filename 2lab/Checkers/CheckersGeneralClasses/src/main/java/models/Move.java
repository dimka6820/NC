package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 16.11.2015.
 */
public class Move implements Serializable {
    private int fromX;
    private int fromY;
    private List<int[]> to;

    public Move() {
        to = new ArrayList<int[]>();
    }

    public int getFromX() {
        return fromX;
    }

    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    public List<int[]> getTo() {
        return to;
    }

    public void setTo(int[] to) {
        this.to.add(to);
    }

    @Override
    public String toString() {
        return "Move{" +
                "fromX=" + fromX +
                ", fromY=" + fromY +
                ", to=" + to +
                '}';
    }
}
