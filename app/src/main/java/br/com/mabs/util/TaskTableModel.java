package br.com.mabs.util;

import br.com.mabs.model.Task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

//This class is for modeling Task Table
public class TaskTableModel extends AbstractTableModel {

    //Columns name to use
    String[] columns = {
        "Nome",
        "Descrição",
        "Prazo",
        "Tarefa concluída",
        "Editar",
        "Excluir"
    };
    List<Task> tasks = new ArrayList<>();

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    //This method is to be editable the "is completed column"
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }    

    //This method return the class that is present in the column index
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (tasks.isEmpty()) {
            return Object.class;
        } else {
            return this.getValueAt(0, columnIndex).getClass();
        }
    }

    //Changing column name and format the deadline date (it's orginal return: yyyy/MM/dd -> from DB)
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return tasks.get(rowIndex).getName();
            case 1:
                return tasks.get(rowIndex).getDescription();
            case 2:
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(tasks.get(rowIndex).getDeadline());
            case 3:
                return tasks.get(rowIndex).isCompleted();
            case 4:
                return "";
            case 5:
                return "";
            default:
                return "Dados não encontrados!";
        }
    }

    //This method is to complete the method  isCellEditable(). It make the change in the box (true or false)
    @Override
    public void setValueAt(Object obj, int rowIndex, int columnIndex) {
        tasks.get(rowIndex).setCompleted((boolean) obj);
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}
