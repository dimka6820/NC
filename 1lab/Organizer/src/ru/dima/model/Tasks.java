package ru.dima.model;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by ִלטענטי on 18.10.2015.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tasks")
public class Tasks {

    @XmlElement(name="task")
    protected List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
