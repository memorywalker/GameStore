package com.aquar.game.main;

import javax.swing.JFrame;

import com.aquar.game.dataserver.DataHandler;

public class MainFrame extends JFrame {
    
    public MainFrame() {
        setSize(500, 500);
        setLocationByPlatform(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        DataHandler.getInstance().init();
        DataHandler.getInstance().release();
        
    }

    
}
