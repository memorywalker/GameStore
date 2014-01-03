package com.aquar.game.main;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.aquar.game.dataserver.DataHandler;

public class MainFrame extends JFrame {
    
    public MainFrame() {
        setSize(500, 550);
        setLocationByPlatform(true);
        setTitle("Entertainment");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setLayout(new FlowLayout());
        DataHandler.getInstance().init();
        
        JPanel GameListContainer = PanelFactory.createGameListPanel();
        mainFrame.add(GameListContainer);
        
        mainFrame.setVisible(true);
        
        DataHandler.getInstance().release();
        
    }

    
}
