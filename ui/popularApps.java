package ui;

import controller.Bank;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class popularApps {
    private Bank bank;

    public void popularPane(Bank bank) {
        this.bank = bank;
        singleDialogInformation();
    }

    public void singleDialogInformation() {
        String[] columnNames = {"app_ID", "product_Name", "Genre"};
        ResultSet rs = bank.getPopularGame();
        JTable gameTable = new JTable();
        DefaultTableModel model = (DefaultTableModel) gameTable.getModel();
        model.setColumnIdentifiers(columnNames);
        try {
            while (rs.next()) {
                Object[] objects = new Object[3];
                for (int i = 0; i < 3; i++) {
                    objects[i] = rs.getObject(i+1);
                }
                model.addRow(objects);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        gameTable.setModel(model);
        JOptionPane.showMessageDialog(null, new JScrollPane(gameTable));
    }
}
