package ru.dima.notificationSystem;

import ru.dima.controllers.HomeController;
import ru.dima.enumStatus.Status;
import ru.dima.forms.FrameNotification;
import ru.dima.model.Task;
import ru.dima.views.ConsoleViews;

import java.util.Date;

/**
 * Created by ִלטענטי on 27.10.2015.
 */
public class NotificationSystem extends Thread {
    HomeController homeController;
  //  ConsoleViews frameNotification;
    FrameNotification frameNotification = new FrameNotification();
    StringBuffer mesage;

    public NotificationSystem(HomeController homeController, ConsoleViews consoleViews) {
        this.homeController = homeController;
       // this.frameNotification = frameNotification;
        mesage = new StringBuffer();
    }

    public static boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            mesage.setLength(0);
            for (Task item : homeController.getModel().getTasks()) {
                int time = (int) (item.getEndDate().getTime() - new Date().getTime()) / (60 * 1000);
                if (time < 15 && item.getStatus() == Status.ACTIVE) {
                   mesage.append("TASK " + item.getName() + " performed by " + time + "mm\n\n");
                }
            }
            if(mesage.length() != 0) frameNotification.printNotation(mesage);
            try {
                sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
