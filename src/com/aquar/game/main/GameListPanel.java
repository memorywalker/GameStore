package com.aquar.game.main;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import org.hibernate.internal.jaxb.mapping.orm.JaxbTable;

import com.aquar.game.database.Company;
import com.aquar.game.database.EnumGameType;
import com.aquar.game.dataserver.DataCache;
import com.aquar.game.ui.dialog.NewGameDialog;
import com.aquar.game.ui.table.CustomCellEditor;
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
        final GameListTable gameTable = new GameListTable();
        GameListTableModel model = new GameListTableModel();
        gameTable.setModel(model);
        EnumGameType[] types = EnumGameType.values();
        JComboBox<EnumGameType> typeBox = new JComboBox<EnumGameType>(types);
        gameTable.getColumnModel().getColumn(GameListTableModel.COL_TYPE).setCellEditor(new CustomCellEditor(typeBox));
        JComboBox<Company> companyBox = new JComboBox<>();
        List<Company> companies = DataCache.getInstance().getComanies();
        if (companies != null) {
            companyBox.setModel(
                    new DefaultComboBoxModel<Company>(
                            (Company[]) companies.toArray(new Company[companies.size()])));
        }
        gameTable.getColumnModel().getColumn(GameListTableModel.COL_COMPANY).setCellEditor(new CustomCellEditor(companyBox));
        gameTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final GameListCtrl ctrl = new GameListCtrl();
        ctrl.setDataModel(model);
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
        delBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIdx = gameTable.getSelectedRow();
                ctrl.delSelectRow(gameTable.convertRowIndexToModel(rowIdx));
            }
        });
        
    }
}
