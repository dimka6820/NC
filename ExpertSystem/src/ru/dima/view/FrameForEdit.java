package ru.dima.view;

import ru.dima.controllers.HomeController;
import ru.dima.models.Fact;
import ru.dima.models.Rule;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Дмитрий on 14.11.2015.
 */
public class FrameForEdit extends JFrame implements ActionListener{
    private HomeController controller;
    private Fact[] facts;

    private Rule rule;
    private Box boxForAnd;
    private Box boxForCheckAnd;
    private JLabel p;
    private JSlider slider;
    private JPanel panelForP;
    private Box boxForP;
    private JPanel selectFact;
    private JComboBox listFactForGoal;
    private JLabel constant;
    private java.util.List<JCheckBox> listForCheckBox;
    private JButton close;
    private JButton ok;
    private JPanel panelForButton;


    public FrameForEdit(Rule rule, HomeController controller) {
        super("Редактирование правила");
        this.rule = rule;
        this.controller = controller;
        getAllFact();
        setSize(new Dimension(500, 500));
        constant = new JLabel("ЕСЛИ ");
        boxForAnd = Box.createVerticalBox();
        boxForAnd.setPreferredSize(new Dimension(240, 300));

        boxForCheckAnd = Box.createVerticalBox();
        JCheckBox jCheckBox;

        listForCheckBox = new ArrayList<>();
        for (int i = 0; i < facts.length; i++) {
            jCheckBox = new JCheckBox(facts[i].getFact());
            for (Integer item : rule.getPremises()) {
                if (item == facts[i].getId()) {
                    jCheckBox.setSelected(true);
                    break;
                }
            }
            boxForCheckAnd.add(jCheckBox);
            listForCheckBox.add(jCheckBox);
        }
        boxForAnd.add(constant);
        boxForAnd.add(new JScrollPane(boxForCheckAnd));
        getContentPane().add(boxForAnd);

        selectFact = new JPanel();
        constant = new JLabel("ТО");
        listFactForGoal = new JComboBox(facts);
        selectFact.add(constant);
        selectFact.add(listFactForGoal);
        panelForP = new JPanel();
        p = new JLabel("С ВЕРОЯТНОСТЬЮ P(%): "); slider = new JSlider(JSlider.HORIZONTAL, 0, 100, (int)(rule.getP()*100));
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);

        panelForP.add(p);
        panelForP.add(slider);

        panelForButton = new JPanel();
        close = new JButton("Отмена");
        close.addActionListener(this);
        ok = new JButton("OK");
        ok.addActionListener(this);
        panelForButton.add(ok);
        panelForButton.add(close);

boxForP = Box.createVerticalBox();
        boxForP.add(selectFact);
        boxForP.add(panelForP);
        boxForP.add(panelForButton);

        getContentPane().add(boxForAnd, BorderLayout.CENTER);
        getContentPane().add(boxForP, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(close)) {
            setVisible(false);
        }
        if (e.getSource().equals(ok)) {
            Fact factGoal = (Fact) listFactForGoal.getSelectedItem();
            rule.setGoal(factGoal.getId());
            editFact(factGoal,rule);
            MainFrame.tableRules.updateUI();
        }

    }
    private void getAllFact() {
        facts = new Fact[controller.getFacts().size()];
        int i = 0;
        for (Fact item : controller.getFacts()) {
            facts[i] = item;
            i++;
        }
    }

    private void editFact(Fact factGoal, Rule rule) {
        rule.getPremises().clear();
        for (JCheckBox item : listForCheckBox) {
            if (item.isSelected()) {
                for (Fact fact : facts) {
                    if (item.getText().equals(fact.getFact())) {
                        rule.setP((double) slider.getValue() / (double) 100);
                        rule.addPrimise(fact.getId());
                        break;
                    }
                }
            }
        }
        setVisible(false);
    }

}
