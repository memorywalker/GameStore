package com.aquar.game.main;

import javax.swing.JPanel;

public class PanelFactory {
    public static JPanel createGameListPanel() {
        GameListPanel gameListPanel = new GameListPanel();
        return gameListPanel.getPanel();
    }
}
