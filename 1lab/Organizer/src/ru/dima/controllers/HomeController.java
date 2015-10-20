package ru.dima.controllers;

import ru.dima.model.Task;
import ru.dima.model.Tasks;
import ru.dima.views.ConsoleViews;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Äìèòðèé on 16.10.2015.
 */
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
        consoleViews.printMenu();

        breakWhile:
        while (true) {
            switch (enterInt()) {
                case 1:
                    consoleViews.printAllTask(model.getTasks());
                    break breakWhile;
                case 2:
                    System.out.println(enterString());
                    break breakWhile;
                case 3:
                    break breakWhile;
                case 4:
                    break breakWhile;
                case 5:
                    adapter.saveXml(model, "Tasks.xml");
                    break breakWhile;
                default:
                    consoleViews.printError("ENTER A NUMBER FROM 1 TO 5");
                    break;
            }
        }
    }

    private void addNewTask() {

    }

    private String enterString()//ÂÎÏÐÎÑ
    {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private int enterInt() //ÂÎÏÐÎÑ
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
