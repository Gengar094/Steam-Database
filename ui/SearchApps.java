package ui;
import controller.Bank;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchApps {
    private Bank bank;

    public void searchPane(Bank bank) {
        this.bank = bank;
        String s = JOptionPane.showInputDialog(null, "Please enter the part of name of app you want to search for");
        System.out.println(s); // should perform query base on name, then use the result to call singleDia
        if (s != null) {
            singleDialogInformation(s);
        }
    }

	public void singleDialogInformation(String keyword) {
        String[] columnNames = {"app_ID", "Name", "Genre","Price"};
        ResultSet rs = bank.searchGame(keyword);
        JTable gameTable = new JTable();
        DefaultTableModel model = (DefaultTableModel) gameTable.getModel();
        model.setColumnIdentifiers(columnNames);
        try {
            while (rs.next()) {
                Object[] objects = new Object[4];
                for (int i = 0; i < 4; i++) {
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
