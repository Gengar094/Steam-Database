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
    public String newVal;

    public ModifyWindow(Bank bank, String playerID) {
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
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.add(this.submit);
        buttonPane.add(Box.createHorizontalStrut(50));
        buttonPane.add(this.quit);
        this.pane.setLayout(new BoxLayout(this.pane, BoxLayout.X_AXIS));
        Object[] options = {"pname", "email", "city"};
        this.box = new JComboBox(options);
        this.box.setMaximumSize(new Dimension(100, 40));
        this.textField = new JTextField("Modify to ...");
        this.textField.setMaximumSize(new Dimension(500, 40));
        this.pane.add(box);

        this.pane.add(this.textField);
        int option = JOptionPane.showConfirmDialog(null, this.pane, "Please fill the field", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option == 0) {
            this.result = box.getSelectedItem().toString();
            this.newVal = textField.getText();
        }
        modifyInfo();
    }


	public void modifyInfo() {
        String newVal = this.textField.getText();
        if (newVal != null) {
            System.out.println("here");
            System.out.println(this.playerID);
            System.out.println(box.getSelectedItem().toString());
            this.bank.modifyProfile(this.playerID, box.getSelectedItem().toString(), newVal);
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, you cannot leave empty field", "Not allowed!", JOptionPane.ERROR_MESSAGE);
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

        else if (field.equals("pname")) {
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
