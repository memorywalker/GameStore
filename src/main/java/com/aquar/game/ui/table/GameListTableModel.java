package com.aquar.game.ui.table;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.aquar.game.database.Company;
import com.aquar.game.database.EnumGameType;
import com.aquar.game.database.Game;
import com.aquar.game.dataserver.DataHandler;
import com.aquar.game.ulti.Ultility;

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
                rowVector.add(COL_NAME, game.getName()); 
                rowVector.add(COL_TYPE, game.getType());
                rowVector.add(COL_DATE, game.getReleaseDate());
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
        Object ret = "";
        Vector rowVector = (Vector) dataVector.elementAt(row);
        Object data =  rowVector.elementAt(column);
        if (data != null) {
            if (COL_TYPE == column) {
                int type = (int) data;
                ret = EnumGameType.getEnum(type);
            } else if (COL_DATE == column) {
                ret = Ultility.getDateStr((Date) data);
            } else {
                ret = data;
            }
        } else {
        }
        
        return ret;
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int column) {
        Object oldValue = getValueAt(row, column);
        // not change anything.
        if (oldValue != null && oldValue.equals(aValue)) {
            return ;
        }
        
        // Get the Object of line.
        Vector rowVector = (Vector) dataVector.elementAt(row);
        Game game = (Game) rowVector.elementAt(COL_DATA);
        switch (column) {
        case COL_NAME:
            game.setName(aValue.toString().trim());
            break;
        case COL_TYPE:
            if (aValue instanceof EnumGameType) {
                EnumGameType type = (EnumGameType) aValue;
                game.setType(type.ordinal());
            }
            break;
        case COL_DATE:
            if (aValue instanceof Date) {
                game.setReleaseDate((Date) aValue);
            }
            break;
        case COL_COMPANY:
            if (aValue instanceof Company) {
                Company company = (Company) aValue;
                game.setCompany(company);
            }
            break;
        default:
            break;
        }
        DataHandler.getInstance().save(game);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        boolean ret = false;
        if (editable) {
            if (column == COL_DATE) {
            } else {
                ret = super.isCellEditable(row, column);
            }
        } else {
        }
        return ret;
        
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
