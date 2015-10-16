package ru.dima.controllers;

import ru.dima.model.Task;
import ru.dima.views.ConsoleViews;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Äìèòðèé on 16.10.2015.
 */
public class HomeController {

    ConsoleViews consoleViews = new ConsoleViews();

    public void start()
    {
        consoleViews.welcome();
        consoleViews.printMenu();

        breakWhile: while (true) {
            switch (enterInt())
            {
                case 1:
                    List<Task> taskList = new ArrayList<Task>();

                    taskList.add(new Task());
                    taskList.add(new Task());
                    taskList.add(new Task());
                    consoleViews.printAllTask(taskList);

                    break breakWhile;
                case 2:
                    break breakWhile;
                case 3:
                    break breakWhile;
                case 4:
                    break breakWhile;
                default:
                    System.out.println("ENTER A NUMBER FROM 1 TO 4");
                    break;
            }
        }
    }

    private int enterInt() //ÂÎÏÐÎÑ
    {
        int number = 0;
        Scanner scanner;
        while (true) {
            try {
                scanner = new Scanner(System.in);
                number = scanner.nextInt();
                return number;
            }

            catch (InputMismatchException ex) {
                System.out.println("ERROR: ENTER NUMBER");
            }
        }
    }
}
