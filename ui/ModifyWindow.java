package ui;
import controller.Bank;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;


public class ModifyWindow extends JFrame{

    public JComboBox box;
    public JPanel pane;
    public JTextField textField;
    public Bank bank;
    public String playerID;
    public JButton quit;
    public JButton submit;
    public String result;

    public ModifyWindow(Bank bank, PlayerWindow window, String playerID) {
        super("Modification");
        this.setLayout(new BorderLayout());
        this.bank = bank;
        this.playerID = playerID;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pane = new JPanel();
        this.quit = new JButton("quit");
        this.quit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.submit = new JButton("submit");
        this.submit.addActionListener(new java.awt.event.ActionListener() {
            private String result;
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = modifyInfo();
                set(result);
            }
        });
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.add(this.submit);
        buttonPane.add(Box.createHorizontalStrut(50));
        buttonPane.add(this.quit);
        this.pane.setLayout(new BoxLayout (this.pane, BoxLayout.X_AXIS));
        Object[] options = {"Username", "email", "city", "country"};
        this.box = new JComboBox(options);
        this.box.setMaximumSize(new Dimension(100, 40));
        this.textField = new JTextField("Modify to ...");
        this.textField.setMaximumSize(new Dimension(500,40));
        this.pane.add(box);
        
        this.pane.add(this.textField);
        add(this.pane, BorderLayout.NORTH);
        add(buttonPane, BorderLayout.SOUTH);
        setSize(new Dimension(500, 200));
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

	public String modifyInfo() {
        String newVal = this.textField.getText();
        if (newVal != null) {
            this.bank.modifyProfile(this.playerID, box.getSelectedItem().toString(), newVal);
            refreshInfo(box.getSelectedItem().toString());
            return "yes";
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, you cannot leave empty field", "Not allowed!", JOptionPane.ERROR_MESSAGE);
            return "fail";
        }
	}

	public void set(String s) {
        this.result = s;
    }

    public void refreshInfo(String field) {
        if (field.equals("Username")) {
            ResultSet set = this.bank.getPlayerInfo(this.playerID);
            String pname = "";
            try {
                while (set.next()) {
                    pname = set.getString("pname");
                }
            } catch (SQLException e) {
                System.out.println("should not happen here");
            }
        }

        else if (field.equals("email")) {
            ResultSet set = this.bank.getPlayerInfo(this.playerID);
            String email = "";
            try {
                while (set.next()) {
                    email = set.getString("email");
                }
            } catch (SQLException e) {
                System.out.println("should not happen here");
            }
        }

        else if (field.equals("city")) {
            ResultSet set = this.bank.getPlayerInfo(this.playerID);
            String city = "";
            try {
                while (set.next()) {
                    city = set.getString("city");
                }
            } catch (SQLException e) {
                System.out.println("should not happen here");
            }
        }

        else if (field.equals("Username")) {
            ResultSet set = this.bank.getPlayerInfo(this.playerID);
            String pname = "";
            try {
                while (set.next()) {
                    pname = set.getString("pname");
                }
            } catch (SQLException e) {
                System.out.println("should not happen here");
            }
        }

        else if (field.equals("country")) {
            ResultSet set = this.bank.getPlayerInfo(this.playerID);
            String country = "";
            try {
                while (set.next()) {
                    country = set.getString("country");
                }
            } catch (SQLException e) {
                System.out.println("should not happen here");
            }
        }
    }

}
