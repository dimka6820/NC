package ru.dima.tableModel;

import ru.dima.controllers.HomeController;
import ru.dima.models.Fact;
import ru.dima.models.Rule;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.*;

/**
 * Created by Дмитрий on 14.11.2015.
 */
public class TableModelForRule implements TableModel {
    private HomeController controller;
    private List<Rule> rules;
    private List<Fact> facts;
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    public TableModelForRule(HomeController controller) {
        this.controller = controller;
        this.rules = controller.getRules();
        this.facts = controller.getFacts();
    }

    @Override
    public int getRowCount() {
        return rules.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return "Правила";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String str = "";
        Rule rule = rules.get(rowIndex);
        if (rule.getPremises().size() == 1) {
            str += "Если " + searchFactName(rule.getPremises().get(0));
        } else {
            str += "Если ";
            for (Integer item : rule.getPremises()) {
                str += " и " + searchFactName(item);
            }
        }
        str += " то " + searchFactName(rule.getGoal()) + " с вероятностью " + rule.getP();

        return str;
    }

    private String searchFactName(int id) {
        for (Fact fact : facts) {
            if (fact.getId() == id) return fact.getFact();
        }
        return "";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    public Rule getRule(int row){
       return rules.get(row);
    }

    public void removeRow(int row) {
        rules.remove(row);
    }
}
