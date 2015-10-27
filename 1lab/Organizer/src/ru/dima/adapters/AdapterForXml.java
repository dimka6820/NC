package ru.dima.adapters;

import ru.dima.model.*;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by ִלטענטי on 16.10.2015.
 */
public class AdapterForXml implements Adapter {
    private File file;

    public AdapterForXml(String nameFile) {
        this.file = new File(nameFile);
    }

    public AdapterForXml(File file) {
        this.file = file;
    }

    public Tasks getTask() throws JAXBException {
        Tasks tasks =  new Tasks();
        JAXBContext jaxbContext = null;
            jaxbContext = JAXBContext.newInstance(new Class[]{Tasks.class, Task.class});
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            tasks = (Tasks) unmarshaller.unmarshal(file);
        return tasks;
    }

    public void saveXml(Object ob) throws JAXBException{
            JAXBContext context = JAXBContext.newInstance(ob.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(ob, file);
    }
}
