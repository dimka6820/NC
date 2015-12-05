package models;

import stasuses.Status;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Дмитрий on 04.11.2015.
 */
public class Player implements Serializable {
    private String login;
    private boolean move;

    private boolean winner;
    private boolean colorChecker;//true - white

    private transient ObjectInputStream inputStream;
    private transient ObjectOutputStream outputStream;
    private Status status;

    public Player(String login, int points) {
        this.login = login;
        move = false;
        this.status = Status.WAITING;
    }

    public boolean isColorChecker() {
        return colorChecker;
    }

    public void setColorChecker(boolean colorChecker) {
        this.colorChecker = colorChecker;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
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

    public void setMove(boolean move) {
        this.move = move;
    }

    public void setLogin(String login) {
        this.login = login;
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
                ", status=" + status +
                '}';
    }
}
