package ru.dima.forms;

import ru.dima.model.Task;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ִלטענטי on 28.10.2015.
 */
public class FrameNotification extends JFrame {
    JTextArea textArea;
    public FrameNotification() {
        super("Note!!!");
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        setLocationRelativeTo(null);
        this.setSize(400,100);
    }

    public void printNotation(StringBuffer str)
    {
        java.awt.Toolkit.getDefaultToolkit().beep();

        textArea.setText(str.toString());
        getContentPane().add(textArea);
        setVisible(true);
    }

}
