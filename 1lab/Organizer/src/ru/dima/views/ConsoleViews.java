package ru.dima.views;

import ru.dima.model.Task;

import java.util.List;

/**
 * Created by ִלטענטי on 16.10.2015.
 */
public class ConsoleViews {
    public void welcome() {
        System.out.println("Welcome!\n");
    }

    public void printMenu() {
        System.out.println("Enter item:\n\n" +
                        "1. View all taskList\n" +
                        "2. Add a new task\n" +
                        "3. Finish a task\n" +
                        "4. Postponed a task"
        );
    }

    public void printAllTask(List<Task> taskList) {
        for (Task task : taskList)
            System.out.println(task.toString());
    }
}
