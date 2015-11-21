package model;

import stasuses.Status;
import stasuses.TypeChecker;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Дмитрий on 04.11.2015.
 */
public class Player implements Serializable {
    private String login;
    private int points;
    private boolean move;
    private TypeChecker checker;

    private transient ObjectInputStream inputStream;
    private transient ObjectOutputStream outputStream;
    private Status status;

    public Player(String login, int points) {
        checker = TypeChecker.BLACK;
        this.login = login;
        this.points = points;
        move = false;
        this.status = Status.WAITING;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public String getLogin() {
        return login;
    }

    public boolean isMove() {
        return move;
    }

    public TypeChecker getChecker() {
        return checker;
    }

    public void setChecker(TypeChecker checker) {
        this.checker = checker;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Player{" +
                ", login='" + login + '\'' +
                ", points=" + points +
                ", status=" + status +
                '}';
    }
}
