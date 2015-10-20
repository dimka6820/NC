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
                        "1. View all task\n" +
                        "2. Add a new task\n" +
                        "3. Finish a task\n" +
                        "4. Postponed a task\n" +
                        "5. Exit"
        );
    }

    public void printAllTask(List<Task> taskList) {
        for (Task item : taskList)
            System.out.println(item.toString());
    }

    public void printError(String error) {
        System.out.println(error);
    }
}
