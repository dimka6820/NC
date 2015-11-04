package model;

import stasuses.Status;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * Created by Дмитрий on 04.11.2015.
 */
public class Player implements Serializable{
    private InetAddress ip;
    private String login;
    private int points;
    private Status status;

    public Player(InetAddress ip, String login, int points) {
        this.ip = ip;
        this.login = login;
        this.points = points;
        this.status = Status.WAITING;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public String getLogin() {
        return login;
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
}
