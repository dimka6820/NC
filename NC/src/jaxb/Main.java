package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Date;

/**
 * Created by Дмитрий on 30.11.2015.
 */
public class Main {
    public static void main(String[] args) throws JAXBException {
        Person person = new Person();
        person.setAge(23);
        person.setName("Dim");
        person.setBirthDay(new Date());

        System.out.println(getTask());
    }
    public static Person getTask() throws JAXBException {
        Person tasks =  new Person();
        JAXBContext  jaxbContext = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        tasks = (Person) unmarshaller.unmarshal(new File("qwerty.xml"));
        return tasks;
    }

    public static void saveXml(Object ob) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(ob.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(ob, new File("qwerty.xml"));
    }
}
