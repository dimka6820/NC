package ru.dima.views;

import ru.dima.enumStatus.Status;
import ru.dima.model.Task;

import java.util.Date;
import java.util.List;

/**
 * Created by ������� on 16.10.2015.
 */
public class ConsoleViews {
    public void welcome() {
        System.out.println("Welcome!\n");
    }

    public void printMenu() {
        System.out.println("\nEnter item:\n\n" +
                        "1. View all task\n" +
                        "2. Add a new task\n" +
                        "3. Edite a task\n" +
                        "0. Exit"
        );
    }

    public void printMenuForEdit() {
        System.out.println("\nEnter item:\n\n" +
                        "1. Delete a task\n" +
                        "2. Finish a task\n" +
                        "3. Postpone the task\n" +
                        "0. Exit"
        );
    }

    public void printAllTask(List<Task> taskList) {
        for (Task item : taskList) {
            System.out.println(item.toString());
        }
    }

    public void printErrorNonId() {
        System.out.println("ERROR: id does not exist");
    }

    public void printErrorEnterNumberFromTo(int one, int two) {
        System.out.println("ENTER A NUMBER FROM " + one + " TO " + two);
    }

    public void printErrorEnterNumber() {
        System.out.println("ENTER A NUMBER");
    }

    public void printError(String s) {
        System.out.println(s);
    }

    public void printEditStatus(Status status) {
        System.out.println("Task Status = " + status);
    }


    public void print(String str) {
        System.out.print(str);
    }


    public void printActiveTask(Task task, int time) {
        java.awt.Toolkit.getDefaultToolkit().beep();
        System.out.println("TASK\n" + task.getName() + "performed by " + time + "mm\n");
    }

    public void printEventAddTask() {
        System.out.println("A new task is created");
    }

    public void printMenuDownload() {
        System.out.println("Choose download type:\n 1. XmlFile\n 2. Serialized objects\n");
    }

    public void printEnterIdForEdit() {
        System.out.println("Enter the task id for edit: ");
    }

    public void printEventForRemove() {
        System.out.println("Task removed");
    }

    public void printErrorForDate() {
        System.out.println("ERROR: You incorrectly typed date");
    }

    public void printFormatDate() {
        System.out.println("EndDateAndTime (FORMAT: dd.MM.yyyy hh:mm) = ");
    }

    public void printErrorFileNotFound() {
        System.out.println("ERROR: FILE NOT FOUND\n");
    }

    public void printEnterFileName() {
        System.out.println("Enter File Name: ");
    }
}
