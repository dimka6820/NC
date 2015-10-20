package ru.dima.model;

import ru.dima.enumStatus.Status;

import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * Created by ִלטענטי on 16.10.2015.
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="task", propOrder = {
        "status",
        "name",
        "description",
        "endDate",
        "startDate",
        "contacts"
})
public class Task {

    @XmlAttribute(required = true)
    private int id;
    @XmlElement
    private Status status;
    @XmlElement
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private Date endDate;
    @XmlElement
    private Date startDate;
    @XmlElement
    private String contacts;


    public Task() {
        super();
    }

    public Task(int id, Status status, String name, String description, Date endDate, Date startDate, String contacts) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.description = description;
        this.endDate = endDate;
        this.startDate = startDate;
        this.contacts = contacts;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "EventTask id=" + id +
                "\nstatus=" + status +
                "\nname='" + name + '\'' +
                "\ndescriptoin='" + description + '\'' +
                "\nstartDate=" + startDate +
                "\nendtDate=" + endDate +
                "\ncontacts='" + contacts + '\'' +
                "\n";
    }
}
