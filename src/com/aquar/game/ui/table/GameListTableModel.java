package com.aquar.game.ui.table;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.aquar.game.database.EnumGameType;
import com.aquar.game.database.Game;

public class GameListTableModel extends DefaultTableModel {
    public static final int COL_NAME = 0;
    public static final int COL_TYPE = COL_NAME + 1;
    public static final int COL_DATE = COL_TYPE + 1;
    public static final int COL_COMPANY = COL_DATE + 1;
    public static final int COL_DATA = COL_COMPANY + 1;
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
                rowVector.add(COL_NAME, game.getName()); // use this col to save the game object
                rowVector.add(COL_TYPE, game.getType());
                rowVector.add(COL_DATE, "");
                rowVector.add(COL_COMPANY, game.getCompany());
                rowVector.add(COL_DATA, game);
                
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
        if (COL_TYPE == column) {
            Vector rowVector = (Vector) dataVector.elementAt(row);
            Object data =  rowVector.elementAt(column);
            if (data != null) {
                int type = (int) data;
                return EnumGameType.getEnum(type);
            }
        }
        return super.getValueAt(row, column);
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int column) {
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
    
    public Object getSelectObject(int row) {
        Object ret = null;
        if (row < dataVector.size()) {
            Vector rowVector = (Vector) dataVector.elementAt(row);
            ret = rowVector.elementAt(COL_DATA);
        }
        return ret;
    }
}
