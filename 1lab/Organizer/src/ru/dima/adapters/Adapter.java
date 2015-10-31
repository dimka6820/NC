package ru.dima.adapters;

import ru.dima.model.Tasks;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ִלטענטי on 27.10.2015.
 */
public interface Adapter {
    Tasks getTask() throws JAXBException, IOException, ClassNotFoundException;
    void saveXml(Object ob) throws JAXBException;
}
