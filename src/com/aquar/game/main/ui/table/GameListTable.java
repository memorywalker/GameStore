package com.aquar.game.main.ui.table;

import javax.swing.JTable;

public class GameListTable extends JTable {
    public GameListTable() {
        // exit edit status when lose focus.
        putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }
    
    
}
