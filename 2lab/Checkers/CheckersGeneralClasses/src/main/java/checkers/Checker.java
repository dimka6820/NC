package checkers;

import java.io.Serializable;

/**
 * Created by Дмитрий on 29.11.2015.
 */
public class Checker implements Serializable{
    private int x;
    private int y;
    private boolean color; //true - white
    private boolean king;
    private boolean canMove;

    public Checker(int x, int y, boolean color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.king = false;
        this.canMove = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isWhite() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean isKing() {
        return king;
    }

    public void setKing() {
        this.king = true;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ')';
    }
}
