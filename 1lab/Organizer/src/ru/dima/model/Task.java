package ru.dima.model;

import ru.dima.enumStatus.Status;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Created by ִלטענטי on 16.10.2015.
 */
public class Task {
    public Task() {
        this.id = 1;
        this.status = Status.ACTIVE;
        this.name = "qwerty";
        this.descriptoin = "qwertyuio";
        this.datetime = new Date();
        this.contacts = "wefwa";
    }

    private int id;
    private Status status;
    private String name;
    private String descriptoin;
    private Date datetime;
    private String contacts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptoin() {
        return descriptoin;
    }

    public void setDescriptoin(String descriptoin) {
        this.descriptoin = descriptoin;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Task id=" + id +
                "\nstatus=" + status +
                "\nname='" + name + '\'' +
                "\ndescriptoin='" + descriptoin + '\'' +
                "\ndatetime=" + datetime +
                "\ncontacts='" + contacts + '\'' +
                "\n";
    }
}
