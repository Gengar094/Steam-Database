package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class SearchApps {


    public void searchPane() {
        String s = JOptionPane.showInputDialog(null, "Please enter the part of name of app you want to search for");
        System.out.println(s); // should perform query base on name, then use the result to call singleDia
        if (s != null) {
            singleDialogInformation();
        }
    }

	public void singleDialogInformation() {
        String[] columnNames = {"app_ID", "Name", "Genre","Price"};
        // enter result as rows
        JTable table = new JTable();
        table.setModel(new DefaultTableModel(null, columnNames));
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
	}

}
