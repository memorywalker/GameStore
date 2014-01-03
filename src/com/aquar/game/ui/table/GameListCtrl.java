package com.aquar.game.ui.table;

import java.util.List;

import com.aquar.game.database.Game;
import com.aquar.game.dataserver.DataHandler;

public class GameListCtrl {
    private GameListTableModel dataModel;
    
    
    public GameListCtrl() {
        
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

    public void refreshData() {
        List<Game> gameList = DataHandler.getInstance().query(new Game());
        dataModel.initData(gameList);
    }
}
