package com.aquar.game.ui.table;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @author Edison
 * Custom a TableCellEditor for get right select when edit.
 */
public class CustomCellEditor extends DefaultCellEditor {

    public CustomCellEditor(JComboBox comboBox) {
        super(comboBox);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        // TODO Auto-generated method stub
        Component com = super.getTableCellEditorComponent(table, value, isSelected, row, column);
        if (com instanceof JComboBox<?>) {
            ((JComboBox<?>) com).setSelectedItem(value);
            Object obj = ((JComboBox) com).getSelectedItem();
            System.out.println(obj);
        }
        return com;
    }

}
