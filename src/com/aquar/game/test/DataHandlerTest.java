package com.aquar.game.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aquar.game.database.Company;
import com.aquar.game.database.Game;
import com.aquar.game.dataserver.DataHandler;


public class DataHandlerTest extends TestCase {

    @Before
    public void setUp() throws Exception {
        DataHandler.getInstance().init();
    }

    @After
    public void tearDown() throws Exception {
        DataHandler.getInstance().release();
    }

    @Test
    public void testSave() {
        boolean ret = false;
        List<Game> games = new ArrayList<Game>();
        String[] names = {"BattleField 4", "Call of Duty 10", "FIFA14", "GTA5"};
        for (String name : names) {
            Game game = new Game();
            game.setName(name);
            games.add(game);
        }
        ret = DataHandler.getInstance().save(games);
        assertTrue(ret);
    }
    
    @Test
    public void testQuery() {
        List<Company> list = DataHandler.getInstance().query(new Company());
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertTrue(true);
    }

}
