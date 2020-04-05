package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class ReadAReview {
    private JFrame frame;
    private JPanel pane;
    private JTextField daysField;
    private JTextField commentField;
    private int days = 0; // need to be changed
    private int assignments = 0;

    public ReadAReview() {
        frame = new JFrame();
    }
    public void singleDialogInformation() {
        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        daysField = new JTextField();
        daysField.setMaximumSize(new Dimension(500,30));

        pane.add(new JLabel("Which app would you like to read reviews? Please write down the app ID"));
        pane.add(Box.createVerticalStrut(20));
        pane.add(daysField);

        int option = JOptionPane.showConfirmDialog(frame, pane, "Please fill all the fields", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {

            String daysInput = daysField.getText();

            try {
                days = Integer.parseInt(daysInput); // perform query
            } catch (NumberFormatException nfe) {
                badOperation();
                return;
            }
            // set up table
            Object[] columnNames = {"review_id", "app_id", "review date", "recommendation", "reviewed by"};
            Object[][] data = {null, null, null, null, null}; // should be done by a query --
            JTable table = new JTable();
            table.setModel(new DefaultTableModel(null, columnNames));
            // add table to pane
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