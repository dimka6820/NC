package ru.dima.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;

/**
 * Created by ִלטענטי on 19.10.2015.
 */

@XmlRegistry
public class ObjectFactory {

    private final static QName qName= new QName("https://github.com/dimka6820/NC.git","https://github.com/dimka6820/NC.git");

    public JAXBElement<Tasks> createData(Tasks value)
    {
        return new JAXBElement<Tasks>(qName, Tasks.class, value);
    }

}
