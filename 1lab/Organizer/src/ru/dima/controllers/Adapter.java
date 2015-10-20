package ru.dima.controllers;

import ru.dima.enumStatus.Status;
import ru.dima.model.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ִלטענטי on 16.10.2015.
 */
public class Adapter {
    protected Tasks getAllTask() {
        Tasks tasks = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(new Class[]{Tasks.class, Task.class});
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            tasks = (Tasks) unmarshaller.unmarshal(new File("Tasks.xml"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tasks;
    }

    protected void saveXml(Object ob, String path) {
        try {
            File file = new File(path);
            JAXBContext context = JAXBContext.newInstance(ob.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(ob, file);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
