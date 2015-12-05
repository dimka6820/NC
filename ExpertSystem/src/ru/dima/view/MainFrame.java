package ru.dima.view;

import ru.dima.controllers.HomeController;
import ru.dima.models.Graph;
import ru.dima.models.Rule;
import ru.dima.tableModel.TableModelForFacts;
import ru.dima.tableModel.TableModelForRule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * Created by Дмитрий on 12.11.2015.
 */
public class MainFrame extends JFrame implements ActionListener {
    private HomeController controller;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem open;
    private JMenuItem create;
    private JMenuItem save;
    private JMenuItem close;

    public static JTable tableFacts;
    private JPanel panelForFacts;
    private JButton removeFact;
    private TableModelForFacts modelForFacts;


    public static JTable tableRules;
    private JPanel panelForRules;
    private JButton removeRules;
    private TableModelForRule modelForRules;
    private JButton editRule;


    private JMenu edit;
    public JMenuItem addFact;
    public JMenuItem addRule;

    public MainFrame() {
        super("ExpertSystem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getMaximumSize());
        setLayout(new BorderLayout());

        tableFacts = new JTable();
        tableRules = new JTable();
        menuBar = new JMenuBar();
        edit = new JMenu("Добавление");

        addFact = new JMenuItem("Добавить факт");
        addFact.addActionListener(this);
        addFact.setEnabled(false);

        addRule = new JMenuItem("Добавить правило");
        addRule.addActionListener(this);
        addRule.setEnabled(false);

        edit.add(addFact);
        edit.add(addRule);

        file = new JMenu("Файл");
        open = new JMenuItem("Открыть");
        open.addActionListener(this);
        create = new JMenuItem("Создать");
        create.addActionListener(this);
        save = new JMenuItem("Сохранить");
        save.addActionListener(this);
        save.setEnabled(false);
        close = new JMenuItem("Закрыть");
        close.addActionListener(this);
        file.add(create);
        file.add(open);
        file.add(save);
        file.add(create);
        file.add(close);

        menuBar.add(file);
        menuBar.add(edit);
        setJMenuBar(menuBar);
        this.addWindowListener(new WindowsListenerImpl());
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(open)) {
            controller = new HomeController(new FrameForOpenXmlFile().getFile());

            addFact.setEnabled(true);
            addRule.setEnabled(true);
            save.setEnabled(true);
            openTableFact();
            openTableRule();
            tableFacts.updateUI();
            tableRules.updateUI();
        }
        if (e.getSource().equals(removeFact)) {
            if(tableRules.getSelectedRow() != -1) {
                modelForFacts.removeRow(tableFacts.getSelectedRow());
                tableFacts.updateUI();
            }
        }
        if (e.getSource().equals(removeRules)) {
            if(tableRules.getSelectedRow() != -1) {
                modelForRules.removeRow(tableRules.getSelectedRow());
                tableRules.updateUI();
            }
        }
        if (e.getSource().equals(save)) {
            controller.close();
        }
        if (e.getSource().equals(create)) {
            Graph graph = new Graph();
            String name = JOptionPane.showInputDialog("Введите имя файла для сохранения:");
            controller = new HomeController(graph, name + ".xml");
            addFact.setEnabled(true);
            addRule.setEnabled(true);
            save.setEnabled(true);
            openTableFact();
            tableFacts.updateUI();
            openTableRule();
            tableRules.updateUI();
        }
        if (e.getSource().equals(addFact)) {
            new FrameForAddFact(controller);
            tableFacts.updateUI();
        }
        if (e.getSource().equals(addRule)) {
            new FrameForAddRule(controller);
        }
        if (e.getSource().equals(close)) {
            if (controller != null) controller.close();
            System.exit(0);
        }
        if (e.getSource().equals(editRule)) {
            if(tableRules.getSelectedRow() != -1) {
                Rule temp= modelForRules.getRule(tableRules.getSelectedRow());
                new FrameForEdit(temp, controller);
            }
        }
    }

    private void openTableFact() {
        modelForFacts = new TableModelForFacts(controller.getFacts());
        tableFacts = new JTable(modelForFacts);
        panelForFacts = new JPanel();
        panelForFacts.setLayout(new BorderLayout());
        JLabel jLabel = new JLabel("Факты: ");
        jLabel.setVerticalAlignment(JLabel.CENTER);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        removeFact = new JButton("Удалить");
        removeFact.addActionListener(this);
        panelForFacts.add(jLabel, BorderLayout.NORTH);
        panelForFacts.add(new JScrollPane(tableFacts), BorderLayout.CENTER);
        panelForFacts.setPreferredSize(new Dimension((this.getWidth() / 2) - 50, this.getHeight() - 50));
        panelForFacts.add(removeFact, BorderLayout.SOUTH);
        getContentPane().add(panelForFacts, BorderLayout.WEST);
        setVisible(true);
    }

    private void openTableRule() {
        modelForRules = new TableModelForRule(controller);
        tableRules = new JTable(modelForRules);
        panelForRules = new JPanel();
        panelForRules.setLayout(new BorderLayout());
        JLabel jLabel = new JLabel("Правила: ");
        jLabel.setVerticalAlignment(JLabel.CENTER);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        removeRules = new JButton("Удалить");
        editRule = new JButton("Редактировать");
        editRule.addActionListener(this);
        removeRules.addActionListener(this);
        JPanel panelBut = new JPanel();
        panelBut.add(editRule);
        panelBut.add(removeRules);
        panelForRules.add(jLabel, BorderLayout.NORTH);
        panelForRules.add(new JScrollPane(tableRules), BorderLayout.CENTER);
        panelForRules.setPreferredSize(new Dimension((this.getWidth() / 2) - 50, this.getHeight() - 50));
        panelForRules.add(panelBut, BorderLayout.SOUTH);
        getContentPane().add(panelForRules, BorderLayout.EAST);
        setVisible(true);
    }

    class WindowsListenerImpl extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            if (controller != null) {
                Object[] options = {"Да", "Нет!"};
                int n = JOptionPane
                        .showOptionDialog(e.getWindow(), "Сохранить файл?",
                                "Подтверждение", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (n == 0) {
                    controller.close();
                    setVisible(false);
                    System.exit(0);
                }
            }
        }
    }

}