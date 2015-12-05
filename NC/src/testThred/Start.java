package testThred;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Дмитрий on 16.11.2015.
 */
public class Start {
    public static List<Integer> list = new CopyOnWriteArrayList<>();
    public static Object object = new Object();
    public static Object object2 = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) list.add(i);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    Start.list.add(i);
            }

        });
        thread.start();
        for (Integer item : list) {
//            System.out.print(item);
        }

        TreeNode parent = new TreeNode();
        TreeNode c = new TreeNode();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("bb");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    synchronized (object2){}
                }
            }

        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object2) {
                    System.out.println("aa");
                    try {
                        Thread.sleep(10);

                    } catch (InterruptedException e) {
                    }
                    synchronized (object){}
                }
            }

        });

t1.start();
        t2.start();
        c.setParent(parent);

    }

}
