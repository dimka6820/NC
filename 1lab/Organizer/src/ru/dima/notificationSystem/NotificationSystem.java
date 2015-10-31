package ru.dima.notificationSystem;

import ru.dima.enumStatus.Status;
import ru.dima.forms.FrameNotification;
import ru.dima.model.Task;
import ru.dima.model.Tasks;

import java.util.Date;

/**
 * Created by ִלטענטי on 27.10.2015.
 */
public class NotificationSystem extends Thread {
    private Tasks model;
    private FrameNotification frameNotification;
    private StringBuffer message;
    public static boolean flag = true;


    public NotificationSystem(Tasks model) {
        frameNotification = new FrameNotification();
        this.model = model;
        message = new StringBuffer();
    }

    @Override
    public void run() {
        while (flag) {
            message.setLength(0);
            for (Task item : model.getTasks()) {
                int time = (int) (item.getEndDate().getTime() - new Date().getTime()) / (60 * 1000);
                if (time < 15 && item.getStatus() == Status.ACTIVE) {
                   message.append("TASK " + item.getName() + " performed by " + time + "mm\n\n");
                }
            }
            if(message.length() != 0) frameNotification.printNotation(message);
            try {
                sleep(30000);
            } catch (InterruptedException e) {
                flag = false;
            }
        }
    }
}
