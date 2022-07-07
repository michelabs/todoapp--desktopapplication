package br.com.mabs.util;

import br.com.mabs.model.Project;
import br.com.mabs.model.Task;
import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

//This class will change the style from Deadline cell, according your situation (on time or late)
public class DeadLineColumnCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int col) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, col);
        label.setHorizontalAlignment(CENTER);
        TaskTableModel taskModel = (TaskTableModel) table.getModel();
        Task task = taskModel.getTasks().get(row);

        if (task.getDeadline().after(new Date()) || task.isCompleted()) {
            label.setBackground(Color.green);
        } else if (task.getDeadline().before(new Date()) && !task.isCompleted()) {
            label.setBackground(Color.red);
        }               
        return label;
    }
}
