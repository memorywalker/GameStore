package com.aquar.game.main.ui.table;

public class GameListCtrl {
    private GameListTableModel dataModel;
    
    public GameListCtrl() {
        // TODO Auto-generated constructor stub
    }
    
    public GameListTableModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(GameListTableModel dataModel) {
        this.dataModel = dataModel;
    }

    public void setEditable(boolean editable) {
        ((GameListTableModel) this.dataModel).setEditable(editable);
    }
    
    public boolean isEditable() {
        return ((GameListTableModel) this.dataModel).isEditable();
    }
}
