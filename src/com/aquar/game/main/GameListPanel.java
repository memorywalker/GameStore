package com.aquar.game.main;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.aquar.game.main.ui.table.GameListCtrl;
import com.aquar.game.main.ui.table.GameListTable;
import com.aquar.game.main.ui.table.GameListTableModel;

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
        gbc.gridwidth = 3;
        mContainer.add(titleLabel, gbc);
        
        // JTable must be in a JScrollPane
        GameListTable gameTable = new GameListTable();
        gameTable.setModel(new GameListTableModel());
        final GameListCtrl ctrl = new GameListCtrl();
        ctrl.setDataModel((GameListTableModel) gameTable.getModel());
        JScrollPane scrollPane = new JScrollPane(gameTable);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        mContainer.add(scrollPane, gbc);
        
        final JButton lockBtn = new JButton("UnLock");
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 2;
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
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        mContainer.add(addBtn, gbc);
        
        JButton delBtn = new JButton("Delete");
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        mContainer.add(delBtn, gbc);
        
    }
}
