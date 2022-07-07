package br.com.mabs.util;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

//This class will change the style from button in the Table and set any icon from this.
public class ButtonColumnCellRenderer extends DefaultTableCellRenderer {

    private String buttonType;

    public ButtonColumnCellRenderer(String buttonType) {
        this.buttonType = buttonType;
    }

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int col) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, col);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIcon(new ImageIcon(getClass().getResource("/" + buttonType + ".png")));
        return label;
    }
}
