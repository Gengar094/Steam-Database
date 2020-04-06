package ui;
import controller.Bank;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadAReview {
    private String playerID;
    private JFrame frame;
    private JPanel pane;
    private JTextField daysField;
    private JTextField commentField;
    private int days = 0; // need to be changed
    private int assignments = 0;
    private Bank bank;

    public ReadAReview(Bank bank) {
        this.bank = bank;
        frame = new JFrame();
    }
    public void singleDialogInformation() {
        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        daysField = new JTextField();
        daysField.setMaximumSize(new Dimension(500,30));

        pane.add(new JLabel("Which game would you like to read reviews? Please write down the game ID"));
        pane.add(Box.createVerticalStrut(20));
        pane.add(daysField);

        int option = JOptionPane.showConfirmDialog(frame, pane, "Please fill all the fields", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {

            String daysInput = daysField.getText();
            // set up table
            ResultSet rs = this.bank.searchReview(daysInput);
            Object[] columnNames = {"review_id", "product name", "player_id", "recommendation", "date"};
            JTable table = new JTable();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setColumnIdentifiers(columnNames);
            try {
                while (rs.next()) {
                    Object[] objects = new Object[5];
                    for (int i = 0; i < 5; i++) {
                        System.out.println("here");
                        objects[i] = rs.getObject(i+1);
                    }
                    model.addRow(objects);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            table.setModel(model);
            // add table to pane
            System.out.println("he2re");
            JScrollPane reviewPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

            pane.add(reviewPane);

            JOptionPane.showMessageDialog(null, reviewPane);
        }
    }

    private void badOperation() {
        JOptionPane.showMessageDialog(null, "Sorry, we cannot find such an app in our database",
        "Error!", JOptionPane.ERROR_MESSAGE);
    }
}