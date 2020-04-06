package ui;
import controller.Bank;
import model.TradeModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryWindow extends JFrame{
    public JButton trade;
    public JButton quit;
    public Bank bank;
    public String playerID;

    public JPanel left;
    public JPanel buttonBoard;
    public JPanel bigPanel;

    public JScrollPane pane;
    public JTable items;
    
    public InventoryWindow() {
        super("Inventory");
    }
    public void main(Bank bank, String playerID) {
        // ****************************** set left panel *******************//
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.bank = bank;
        this.playerID = playerID;

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

        ResultSet rs = bank.getInventory(playerID);
        String[] columnNames = {"item_id", "item type", "tradability"};
        JTable table = new JTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
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
        table.setModel(model);
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

            String itemID = itemField.getText();
            String playerID = playerField.getText();

            if (playerID.equals(this.playerID)) {
                System.out.println("bad");
                badOperation("trade with self");
                return;
            }

            try {
                bank.giftItem(new TradeModel(this.playerID, playerID, itemID, java.time.LocalDate.now().toString()));

                JOptionPane.showMessageDialog(null, "successful");
            } catch (SQLException e) {
                if (e.getErrorCode() == 1) {
                    badOperation("not owned");
                } else if (e.getErrorCode() == 2291) {
                    badOperation("not found");
                }
            }

        }
    }

    private void badOperation(String type) {
        if (type == "not found") {
            JOptionPane.showMessageDialog(null, "Sorry, we cannot find such an item or player in our database",
                    "Error!", JOptionPane.WARNING_MESSAGE);
        } else if (type == "not owned") {
            JOptionPane.showMessageDialog(null, "Sorry, you do not own the item",
                    "Error!", JOptionPane.WARNING_MESSAGE);
        } else if (type == "trade with self") {
            JOptionPane.showMessageDialog(null, "Sorry, you cannot trade with yourself",
                    "Error!", JOptionPane.WARNING_MESSAGE);
        }
    }
}