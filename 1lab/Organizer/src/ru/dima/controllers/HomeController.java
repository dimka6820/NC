package ru.dima.controllers;

import ru.dima.adapters.*;
import ru.dima.enumStatus.Status;
import ru.dima.model.Task;
import ru.dima.model.Tasks;
import ru.dima.notificationSystem.NotificationSystem;
import ru.dima.views.ConsoleViews;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by ִלטענטי on 16.10.2015.
 */

public class HomeController {

    private Scanner scanner = new Scanner(System.in);
    private ConsoleViews consoleViews;
    private Adapter adapter;
    private Tasks model;

    public HomeController() {
        super();
    }

    public HomeController(ConsoleViews consoleViews, Adapter adapter, Tasks model) {
        this.consoleViews = consoleViews;
        this.adapter = adapter;
        this.model = model;
        scanner = new Scanner(System.in);
    }

    public void start() {
        consoleViews.welcome();

        while (true) {
            consoleViews.printMenu();
            switch (enterInt()) {
                case 1:
                    consoleViews.printAllTask(model.getTasks());
                    break;
                case 2:
                    if (addNewTask()) consoleViews.print("A new task is created");
                    break;
                case 3:
                    consoleViews.printAllTask(model.getTasks());
                    consoleViews.print("Enter the task id for finish: ");
                    editTask(enterInt());
                    break;
                case 0:
                    try {
                        adapter.saveXml(model);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                    NotificationSystem.flag = false;
                    return;
                default:
                    consoleViews.printErrorEnterNumberFromTo(0,3);
                    break;
            }
        }
    }

    private void editTask(int editId) {

        while (true) {
            consoleViews.printMenuForEdit();

            switch (enterInt()) {
                case 1:
                    if (deleteTask(editId)) {
                        consoleViews.print("Task removed");
                    } else consoleViews.printErrorNonId();
                    return;
                case 2:
                    if (editStatusTask(editId, Status.INACTIVE)) {
                        consoleViews.printEditStatus(Status.INACTIVE);
                    } else consoleViews.printErrorNonId();
                    return;
                case 3:
                    if (editStatusTask(editId, Status.POSTPONED)) {
                        consoleViews.printEditStatus(Status.POSTPONED);
                    } else consoleViews.printErrorNonId();
                    return;
                case 0:
                    return;
                default:
                    consoleViews.printErrorEnterNumberFromTo(0, 3);
                    break;
            }
        }
    }

    private boolean editStatusTask(int taskId, Status status) {
        for (Task item : model.getTasks()) {
            if (taskId == item.getId()) {
                item.setStatus(status);
                return true;
            }
        }
        return false;
    }

    private boolean deleteTask(int taskId) {
        for (Task item : model.getTasks()) {
            if (taskId == item.getId()) {
                model.getTasks().remove(item);
                return true;
            }
        }
        return false;
    }


    private boolean addNewTask() {
        Task tempTask = new Task();
        int currentId = 0;
        for (Task item : model.getTasks())
            if (currentId < item.getId()) currentId = item.getId();

        tempTask.setId(++currentId);
        tempTask.setStartDate(new Date());
        tempTask.setStatus(Status.ACTIVE);

        consoleViews.print("Name = ");
        tempTask.setName(enterString());
        consoleViews.print("Description = ");
        tempTask.setDescription(enterString());
        consoleViews.print("Contacts = ");
        tempTask.setContacts(enterString());

        consoleViews.print("EndDateAndTime (FORMAT: dd.MM.yyyy hh:mm) = ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.ENGLISH);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Date date;
        try {
            date = sdf.parse(br.readLine());
        } catch (ParseException e) {
            consoleViews.printError("ERROR: You incorrectly typed date");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        tempTask.setEndDate(date);

        model.getTasks().add(tempTask);
        return true;
    }

    private String enterString() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private int enterInt() {
        while (true) {
            try {
                scanner = new Scanner(System.in);
                return scanner.nextInt();
            } catch (InputMismatchException ex) {
                consoleViews.printError("ERROR: ENTER NUMBER");
            }
        }
    }

    public Tasks getModel() {
        return model;
    }
}
