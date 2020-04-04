import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.*;

public class InventoryWindow extends JFrame{
    public JButton trade;
    public JButton quit;

    public JPanel left;
    public JPanel buttonBoard;
    public JPanel bigPanel;

    public JScrollPane pane;
    public JTable items;
    
    public InventoryWindow() {
        super("Inventory");
    }
    public void main() {
        // ****************************** set left panel *******************//
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.buttonBoard = new JPanel();
        this.buttonBoard.setLayout(new BoxLayout(this.buttonBoard, BoxLayout.Y_AXIS));
        this.trade = new JButton("trade");
        this.trade.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trade();
            }
        });
        this.quit = new JButton("quit");
        this.quit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.buttonBoard.add(this.trade);
        this.buttonBoard.add(Box.createVerticalStrut(20));
        this.buttonBoard.add(this.quit);

        // ****************************** set right panel *******************//
        this.left = new JPanel();
        Object[] columnNames = {"item_id", "player_id", "item type", "tradability"};
        Object[][] data = {null, null, null, null, null}; // should be done by a query --
        JTable table = new JTable();
        table.setModel(new DefaultTableModel(null, columnNames));
        // add table to pane
        JScrollPane reviewPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        left.setLayout(new BorderLayout());

        left.add(reviewPane, BorderLayout.CENTER);
    
        // ****************************** set big panel *******************//
        this.bigPanel = new JPanel();
        this.bigPanel.setLayout(new BorderLayout());
        bigPanel.add(this.left, BorderLayout.WEST);
        bigPanel.add(this.buttonBoard, BorderLayout.EAST);
        this.setSize(new Dimension(600, 700));
        add(bigPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void trade() {
        JPanel tradePane = new JPanel();
        tradePane.setLayout(new BoxLayout(tradePane, BoxLayout.Y_AXIS));

        JTextField itemField = new JTextField();
        JTextField playerField = new JTextField();

        tradePane.add(new JLabel("Please select an item you want to trade"));
        tradePane.add(itemField);

        tradePane.add(new JLabel("Please write down the player id to the player you want to trade with."));
        tradePane.add(playerField);

        int option = JOptionPane.showConfirmDialog(null, tradePane, "Trade System", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {

            String daysInput = itemField.getText();
            String assignmentsInput = playerField.getText();

            try {
                int days = Integer.parseInt(daysInput);
                int assignments = Integer.parseInt(assignmentsInput); // perform query
            } catch (NumberFormatException nfe) {
                badOperation();
                return;
            }

            JPanel pane = new JPanel();

            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

            pane.add(new JLabel("You have successfully added a review"));

            JOptionPane.showMessageDialog(null, pane);
        }
    }

    private void badOperation() {
        JOptionPane.showMessageDialog(null, "Sorry, we cannot find such an item or player in our database",
        "Error!", JOptionPane.WARNING_MESSAGE);
    }
}