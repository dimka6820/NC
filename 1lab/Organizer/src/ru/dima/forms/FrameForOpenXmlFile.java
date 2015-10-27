package ru.dima.forms;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by ִלטענטי on 27.10.2015.
 */
public class FrameForOpenXmlFile extends JFrame {
    private File file;

    public FrameForOpenXmlFile() {
        JFileChooser dialog = new JFileChooser();
        dialog.showOpenDialog(this);
        file = dialog.getSelectedFile();
    }

    public File getFile() {
        return file;
    }
}
