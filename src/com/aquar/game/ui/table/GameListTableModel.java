package com.aquar.game.ui.table;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import com.aquar.game.database.Game;

public class GameListTableModel extends DefaultTableModel {
    public static final int COL_NAME = 0;
    public static final int COL_TYPE = COL_NAME + 1;
    public static final int COL_DATE = COL_TYPE + 1;
    public static final int COL_COMPANY = COL_DATE + 1;
    // not edit the cell by default
    private boolean editable = false;
    
    public GameListTableModel() {
        initColNames();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void initData(List<Game> gameList) {
        dataVector.clear();
        if (gameList != null) {
            for (Game game : gameList) {
                Vector rowVector = new Vector();
                rowVector.add(COL_NAME, game);
                rowVector.add(COL_TYPE, game.getType());
                rowVector.add(COL_DATE, "");
                rowVector.add(COL_COMPANY, game.getCompany());
                
                dataVector.add(rowVector);
            }
        }
        fireTableDataChanged();
    }

    @SuppressWarnings("unchecked")
    private void initColNames() {
        columnIdentifiers.add("Name");
        columnIdentifiers.add("Type");
        columnIdentifiers.add("Date");
        columnIdentifiers.add("Company");
        
    }
    
    @Override
    public int getRowCount() {
        return super.getRowCount();
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        // TODO Auto-generated method stub
        return super.getValueAt(row, column);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        if (editable) {
            return super.isCellEditable(row, column);
        } else {
            return false;
        }
        
    }
    
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
