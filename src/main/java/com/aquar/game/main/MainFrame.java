package com.aquar.game.main;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.aquar.game.dataserver.DataHandler;

public class MainFrame extends JFrame {
    
    private static MainFrame mainFrame;

    public static MainFrame getInstance() {
        return mainFrame;
    }
    
    public MainFrame() {
        setSize(500, 550);
        setLocationByPlatform(true);
        setTitle("Entertainment");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                DataHandler.getInstance().release();
                System.out.println("release successfully.");
                super.windowClosed(e);
            }
            
        });
    }

    public static void main(String[] args) {
        mainFrame = new MainFrame();
        mainFrame.setLayout(new FlowLayout());
        DataHandler.getInstance().init();
        
        JPanel GameListContainer = PanelFactory.createGameListPanel();
        mainFrame.add(GameListContainer);
        
        mainFrame.setVisible(true);
        
//        DataHandler.getInstance().release();
        
    }

    
}
