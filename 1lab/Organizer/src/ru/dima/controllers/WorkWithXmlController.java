package ru.dima.controllers;

import ru.dima.model.Task;
import ru.dima.model.Tasks;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by ִלטענטי on 16.10.2015.
 */
public class WorkWithXmlController {
    protected Tasks getAllTask() {
        final String pack = Tasks.class.getPackage().getName();
        Tasks tasks = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] {Tasks.class, Task.class});
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            tasks = (Tasks) unmarshaller.unmarshal(new File("Events.xml"));
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }

      //  System.out.println(tasks.toString());
        return tasks;
    }

    protected void addNewTask(Task task) {

    }

    protected void deleteTask(Task task) {

    }

    protected void editTask(Task task) {

    }
}
