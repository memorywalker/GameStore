package com.aquar.game.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MenuItem;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListDataListener;

import com.aquar.game.database.Company;
import com.aquar.game.database.EnumGameType;
import com.aquar.game.database.Game;
import com.aquar.game.dataserver.DataCache;
import com.aquar.game.dataserver.DataHandler;

public class NewGameDialog extends JDialog {
    
    public NewGameDialog(JFrame owner) {
        super(owner, "New Game", true);
        setLocationRelativeTo(owner);
        initUI();
    }
    
    private void initUI() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        final JTextField nameField = new JTextField(5);
        addItemInput("Name", nameField, formPanel, gbc);
        
        gbc.gridy = 1;
        EnumGameType[] types = EnumGameType.values();
        final JComboBox<EnumGameType> typeBox = new JComboBox<EnumGameType>(types);
        addItemInput("Type", typeBox, formPanel, gbc);
        
        gbc.gridy = 2;
        JButton dateBtn = new JButton("Date");
        addItemInput("ReleaseDate", dateBtn, formPanel, gbc);
        
        gbc.gridy = 3;
        final JComboBox<Company> companyBox = new JComboBox<>();
        List<Company> companies = DataCache.getInstance().getComanies();
        if (companies != null) {
            companyBox.setModel(
                    new DefaultComboBoxModel<Company>(
                            (Company[]) companies.toArray(new Company[companies.size()])));
        }
        
        addItemInput("Company", companyBox, formPanel, gbc);
        
        getContentPane().add(formPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton saveBtn = new JButton("Save");
        bottomPanel.add(saveBtn);
        saveBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    dispose();
                }
                Game game = new Game();
                game.setCompany((Company) companyBox.getSelectedItem());
                game.setName(name);
                game.setReleaseDate(new Date());
                game.setType(((EnumGameType)typeBox.getSelectedItem()).ordinal());
                DataHandler.getInstance().save(game);
                dispose();
            }
        });
        
        JButton cancelBtn = new JButton("Cancel");
        bottomPanel.add(cancelBtn);
        
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        pack();
        setResizable(false);
    }

    private void addItemInput(String name, Component input, JPanel formPanel, final GridBagConstraints gbc) {
        gbc.gridx = 0;
        JLabel nameLabel = new JLabel(name);
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(input, gbc);
    }
    
}
