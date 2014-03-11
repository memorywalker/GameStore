package com.aquar.game.dataserver;

import java.util.List;

import javax.management.InstanceAlreadyExistsException;

import com.aquar.game.database.Company;

public class DataCache {
    private static DataCache instance;
    private List<Company> companies = null;
    private DataCache() {
        refreshData();
    }
    
    private void refreshData() {
        companies = DataHandler.getInstance().query(new Company());
    }
    
    public static DataCache getInstance() {
        if (instance == null) {
            instance = new DataCache();
        }
        return instance;
    }
    
    public List<Company> getComanies() {
        return companies;
    }
}
