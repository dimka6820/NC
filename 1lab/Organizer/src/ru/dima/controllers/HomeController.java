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
 * Created by Дмитрий on 16.10.2015.
 */


public class HomeController {

    /**
     * Чтение с клавиатуры @see #Scanner(InputStream source)
     */
    private Scanner scanner;
    /**
     * Представление
     */
    private ConsoleViews consoleViews;
    /**
     * Адаптер, для чтения и сохранен в файл
     */
    private Adapter adapter;
    /**
     * Модель
     */
    private Tasks model;

    public HomeController() {
        super();
    }

    /**
     * @param consoleViews выбор класса представления
     * @param adapter выбор адаптера
     * @param model выбор модели для работы
     */
    public HomeController(ConsoleViews consoleViews, Adapter adapter, Tasks model) {
        this.consoleViews = consoleViews;
        scanner = new Scanner(System.in);
        this.adapter = adapter;
        this.model = model;
        scanner = new Scanner(System.in);
    }

    /**
     * <p>Проверяет выбор пользователя</p>
     */
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


    /**
     * <p>Оедактирование задачи, выбор что делать с выбранной задачей</p>
     * @param editId Id редактируемой задачи
     */
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

    /**
     * <p>Изменение статуса задачи</p>
     * @param taskId id задачи
     * @param status статус
     * @return Возвращает true, если метод нашел задачу, false, если задачи с данным id не существует
     */
    private boolean editStatusTask(int taskId, Status status) {
        for (Task item : model.getTasks()) {
            if (taskId == item.getId()) {
                item.setStatus(status);
                return true;
            }
        }
        return false;
    }

    /**
     * <p>Удаление задачи</p>
     * @param taskId id задачи
     * @return Возвращает true, если метод нашел и удалил задачу, false, если задачи с данным id не существует
     */
    private boolean deleteTask(int taskId) {
        for (Task item : model.getTasks()) {
            if (taskId == item.getId()) {
                model.getTasks().remove(item);
                return true;
            }
        }
        return false;
    }


    /**
     * <p>Добавление новой задачи</p>
     * @return  Возвращает true, если задача добавлена, false, если данные введены некоректно и задача не добавилась
     */
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

    /**
     * @return строку, введеную с класиатуры
     */
    private String enterString() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * @return число, введеное с клавиатуры
     */
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
