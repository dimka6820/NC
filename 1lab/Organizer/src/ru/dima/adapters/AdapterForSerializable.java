package ru.dima.adapters;

import ru.dima.model.Tasks;

import java.io.*;

/**
 * Created by ִלטענטי on 27.10.2015.
 */
public class AdapterForSerializable implements Adapter{
    private String name;
    public AdapterForSerializable(String name) {
        this.name = name;
    }

    @Override
    public Tasks getTask() throws IOException, ClassNotFoundException {
        Tasks tasks = null;

            FileInputStream fis = new FileInputStream(name);
            ObjectInputStream oin = new ObjectInputStream(fis);
            tasks = (Tasks) oin.readObject();


        return tasks;
    }

    @Override
    public void saveXml(Object ob)  {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(name);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ob);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
