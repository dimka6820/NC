package ru.dima.controllers;

import ru.dima.adapters.*;
import ru.dima.forms.FrameForOpenXmlFile;
import ru.dima.model.Tasks;
import ru.dima.notificationSystem.NotificationSystem;
import ru.dima.views.ConsoleViews;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StartController {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        java.awt.Toolkit.getDefaultToolkit().beep();

        ConsoleViews consoleViews = new ConsoleViews();
        Adapter adapter = null;
        Tasks model = null;
        boolean flag = true;


        consoleViews.print("Choose download type:\n 1. XmlFile\n 2. Serialized objects\n");
        int enter = enterInt();
        scanner = new Scanner(System.in);

        while (flag) {
            switch (enter) {
                case 1:
                    adapter = new AdapterForXml(new FrameForOpenXmlFile().getFile());
                    try {
                        model = adapter.getTask();
                    } catch (JAXBException e) {
                        consoleViews.print("ERROR: FILE NOT FOUND\n");
                        break;
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                    flag = false;
                    break;
                case 2:
                    while (true) {
                        consoleViews.print("Enter File Name: ");
                        String name = scanner.nextLine();

                        adapter = new AdapterForSerializable(name);
                        try {
                            model = adapter.getTask();
                            flag = false;
                            break;
                        } catch (JAXBException | ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            consoleViews.print("ERROR: FILE NOT FOUND\n");
                            continue;
                        }
                    }
                    break;
                default:
                    consoleViews.printErrorEnterNumberFromTo(1, 2);
                    flag = false;
                    break;
            }
        }

        HomeController homeController = new HomeController(consoleViews, adapter, model);
        NotificationSystem notificationSystem = new NotificationSystem(homeController, consoleViews);
        notificationSystem.start();
        homeController.start();
    }

    private static int enterInt() {
        while (true) {
            try {
                scanner = new Scanner(System.in);
                return scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("ERROR: ENTER NUMBER");
            }
        }
    }
}
