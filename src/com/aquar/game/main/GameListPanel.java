package com.aquar.game.main;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.aquar.game.ui.dialog.NewGameDialog;
import com.aquar.game.ui.table.GameListCtrl;
import com.aquar.game.ui.table.GameListTable;
import com.aquar.game.ui.table.GameListTableModel;

public class GameListPanel {
    private JPanel mContainer;
    
    public GameListPanel() {
        initUI();
    }
    
    public JPanel getPanel(){
        return mContainer;
    }
    
    private void initUI() {
        mContainer = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel titleLabel = new JLabel("Game List");
        titleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        mContainer.add(titleLabel, gbc);
        
        // JTable must be in a JScrollPane
        GameListTable gameTable = new GameListTable();
        gameTable.setModel(new GameListTableModel());
        final GameListCtrl ctrl = new GameListCtrl();
        ctrl.setDataModel((GameListTableModel) gameTable.getModel());
        ctrl.refreshData();
        JScrollPane scrollPane = new JScrollPane(gameTable);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mContainer.add(scrollPane, gbc);
        
        final JButton lockBtn = new JButton("UnLock");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridwidth = 1;
        mContainer.add(lockBtn, gbc);
        lockBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ctrl.isEditable()) {
                    ctrl.setEditable(false);
                    lockBtn.setText("UnLock");
                } else {
                    ctrl.setEditable(true);
                    lockBtn.setText("Lock");
                }
                
            }
        });
        
        JButton addBtn = new JButton("Add");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        mContainer.add(addBtn, gbc);
        addBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parentWindow = SwingUtilities
                        .windowForComponent(mContainer);
                JFrame parentFrame = null;
                if (parentWindow instanceof JFrame) {
                    parentFrame = (JFrame) parentWindow;
                    NewGameDialog dialog = new NewGameDialog(parentFrame);
                    dialog.setVisible(true);
                }
            }
        });
        JButton refreshBtn = new JButton("Refresh");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        mContainer.add(refreshBtn, gbc);
        refreshBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrl.refreshData();
            }
        });
         
        JButton delBtn = new JButton("Delete");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 2;
        mContainer.add(delBtn, gbc);
        
    }
}
