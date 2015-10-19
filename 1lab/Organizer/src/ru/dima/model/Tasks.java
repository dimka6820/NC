package ru.dima.model;

import com.sun.javafx.tk.Toolkit;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ִלטענטי on 18.10.2015.
 */

@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tasks", propOrder = {"taskList"})
public class Tasks {

    @XmlElement(name="task", type = Task.class)
    protected List<Task> taskList;

    public Tasks() {
        this.taskList = new ArrayList<Task>();
    }


    public List<Task> getTaskList() {
        return taskList;
    }

//    public void setTasks(final Listener listener) {
//        taskList = (listener == null) ? null : new ArrayList<Task>()
//        {
//            @Override
//            public boolean add(Task task) {
//                listener.handlePurchaseOrder(Tasks.this, task);
//                return false;
//            }
//        };
//    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public static interface Listener {
        void handlePurchaseOrder(Tasks tasks, Task task);
    }

    @Override
    public String toString() {
        String s = "";
//        for (Task item : taskList)
//            s += item.toString();
        System.out.println(taskList.size());
        return s;
    }
}
