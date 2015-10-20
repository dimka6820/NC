package ru.dima.controllers;

import ru.dima.enumStatus.Status;
import ru.dima.model.Task;
import ru.dima.model.Tasks;
import ru.dima.views.ConsoleViews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Дмитрий on 16.10.2015.
 */


// Зачем завершить и удалить

public class HomeController {

    private Scanner scanner;
    private ConsoleViews consoleViews = new ConsoleViews();
    private Adapter adapter = new Adapter();
    private Tasks model;

    public HomeController() {
        model = adapter.getAllTask();
        scanner = new Scanner(System.in);
    }

    public void start() {
        consoleViews.welcome();

        breakWhile:
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
                    if (deleteTask()) consoleViews.print("Task removed");
                    else consoleViews.printError("ERROR: id does not exist");
                        break;
                case 4:
                    finishTask();
                    break;
                case 5:
                    break;
                case 0:
                    adapter.saveXml(model, "Tasks.xml");
                    break breakWhile;
                default:
                    consoleViews.printError("ENTER A NUMBER FROM 0 TO 5");
                    break;
            }
        }
    }

    private boolean finishTask() {
        int tempId;
        consoleViews.printAllTask(model.getTasks());

        consoleViews.print("Enter the task id for finish:");
        tempId = enterInt();

        for (Task item : model.getTasks()) {
            if (tempId == item.getId()) {
                item.setStatus(Status.INACTIVE);
                return true;
            }
        }
        return false;
    }

    private boolean deleteTask() {
        int tempId;
        consoleViews.printAllTask(model.getTasks());

        consoleViews.print("Enter the task id for delete:");
        tempId = enterInt();

        for (Task item : model.getTasks()) {
            if (tempId == item.getId()) {
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
        Date date = null;
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

    private String enterString()//ВОПРОС
    {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private int enterInt() //ВОПРОС
    {
        int number = 0;
        while (true) {
            try {
                scanner = new Scanner(System.in);
                number = scanner.nextInt();
                return number;
            } catch (InputMismatchException ex) {
                consoleViews.printError("ERROR: ENTER NUMBER");
            }
        }
    }
}
