package com.aquar.game.ui.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomCellRender extends DefaultTableCellRenderer {
    private Color oddLineColor = new Color(45, 210, 150);
    private Color evenLineColor = new Color(210, 150, 40);
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // TODO Auto-generated method stub
        Component com =  super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);
        if (row % 2 == 0) {
            com.setBackground(oddLineColor);
        } else {
            com.setBackground(evenLineColor);
        }
        return com;
    }
}
