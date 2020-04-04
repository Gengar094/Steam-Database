import javax.swing.*;
import java.awt.*;

public class WriteReviewWindow {

    private JFrame frame;
    private JPanel pane;
    private JTextField daysField;
    private JTextField commentField;
    private int days = 0; // need to be changed
    private int assignments = 0;

    public WriteReviewWindow() {
        
    }
    public void singleDialogInformation() {
        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        daysField = new JTextField();
        commentField = new JTextField();
        commentField.setPreferredSize(new Dimension(500,100));

        pane.add(new JLabel("Which app would you like to write a review? Please write down the app ID"));
        pane.add(daysField);

        pane.add(new JLabel("Please write down your comment"));
        pane.add(commentField);

        int option = JOptionPane.showConfirmDialog(frame, pane, "Please fill all the fields", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {

            String daysInput = daysField.getText();
            String assignmentsInput = commentField.getText();

            try {
                days = Integer.parseInt(daysInput);
                assignments = Integer.parseInt(assignmentsInput); // perform query
            } catch (NumberFormatException nfe) {
                badOperation();
                return;
            }

            pane = new JPanel();
            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

            pane.add(new JLabel("You have successfully traded an item"));

            JOptionPane.showMessageDialog(frame, pane);
        }
    }

    private void notOwned() {
        JOptionPane.showMessageDialog(null, "Sorry, you cannot leave comment without having the product",
        "Warning!", JOptionPane.WARNING_MESSAGE);
    }
    
    private void badOperation() {
        JOptionPane.showMessageDialog(null, "Sorry, we cannot find such an app in our database",
        "Error!", JOptionPane.ERROR_MESSAGE);
    }
}