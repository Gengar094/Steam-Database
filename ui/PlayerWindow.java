package ui;
import controller.Bank;
import database.DatabaseConnectionHandler;
import model.InGroupModel;
import model.PurchaseModel;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


import java.awt.image.BufferedImage;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PlayerWindow extends JFrame {
    public int playerID;
    public String username;
    public String password;
    public Bank bank;

    public JPanel info; // player info
    public JPanel i;
    public JPanel personalInfo;
    public JPanel subInfo;
    public JPanel func; // Inventory / write review
    public JPanel gameView; // Games / Groups .....
    public JPanel otherApp;
    public JPanel groupView;
    public JPanel controlPanel;
    public JPanel bigPanel; // overall panel

    public JLabel icon;
    public JLabel pnameLabel;
    public JLabel idLabel;
    public JLabel emailLabel;
    public JLabel cityLabel;
    public JLabel countryLabel;
    public JLabel games;
    public JLabel groups;

    public JButton modify;
    public JButton buy;
    public JButton delete;
    public JButton searchGames;
    public JButton join;
    public JButton quit;
    public JButton writeReview;
    public JButton inventory;
    public JButton read;

    public JScrollPane gamePane;
    public JScrollPane groupPane;
    public JScrollPane appPane;
    public JTable gameTable;
    public JTable groupTable;
    public JTable appTable;

    public PlayerWindow(int playerID, String username, String password, DatabaseConnectionHandler dch) {
        super("Player Profile");
        this.username = username;
        this.password = password;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setLayout(new GridBagLayout());
        // GridBagConstraints gbc = new GridBagConstraints();
        this.bigPanel = new JPanel();

        this.info = new JPanel();
        this.i = new JPanel();
        this.i.setLayout(new BoxLayout(this.i, BoxLayout.X_AXIS));
        this.bank = new Bank(dch);
        try {
            this.icon = new JLabel();
            BufferedImage logo = chooseAvatar(playerID);
            JLabel picLabel = new JLabel(new ImageIcon(logo));
            //imgPanel.add(picLabel);
            System.out.println("x");
            this.i.add(picLabel);
        }catch (IOException e){
            System.out.println(e);
        }

        this.playerID = playerID;
        String pname = "pname";
        String email = "email";
        String city = "city";
        String country = "country";
        // try {
        // ResultSet arr = performPlayerQuery(playerID); // assume to be array
        // while (arr.next()) {
        // pname = arr.getString("pname");
        // email = arr.getString("email");
        // city = arr.getString("city");
        // country = arr.getString("country");
        // }
        // } catch (Exception e) {
        // System.out.println("Exception :" + e.getMessage());
        // }

        this.pnameLabel = new JLabel(pname);
        this.idLabel = new JLabel(Integer.toString(playerID));
        this.emailLabel = new JLabel(email);
        this.cityLabel = new JLabel("                   " + city);
        this.countryLabel = new JLabel(country);
        this.modify = new JButton("modify");
        this.modify.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyWindow modify = new ModifyWindow();
                modify.modifyInfo();
            }
        });


        // ********************** set personal info ******************* //
        this.subInfo = new JPanel();
        JLabel comma = new JLabel(", ");
        this.subInfo.setLayout(new BoxLayout(this.subInfo, BoxLayout.X_AXIS));
        this.subInfo.add(this.cityLabel);
        this.subInfo.add(comma);
        this.subInfo.add(this.countryLabel);

        this.personalInfo = new JPanel();
        this.personalInfo.setLayout(new BoxLayout(this.personalInfo, BoxLayout.Y_AXIS));
        this.personalInfo.add(this.pnameLabel);
        this.personalInfo.add(Box.createVerticalStrut(10));
        this.personalInfo.add(this.idLabel);
        this.personalInfo.add(Box.createVerticalStrut(10));
        this.personalInfo.add(this.emailLabel);
        this.personalInfo.add(Box.createVerticalStrut(10));
        this.personalInfo.add(subInfo);
        this.personalInfo.add(Box.createVerticalStrut(10));
        this.personalInfo.add(this.modify);
        this.i.add(personalInfo);
        this.info.add(i);

        // ********************** set three buttons (review and inventory and read)
        // ******************* //
        this.func = new JPanel();
        this.func.setLayout(new BoxLayout(this.func, BoxLayout.Y_AXIS));
        this.inventory = new JButton("Inventory");
        this.inventory.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventoryWindow inventory = new InventoryWindow();
                inventory.main(bank, Integer.toString(playerID));
            }
        });
        this.inventory.setMaximumSize(new Dimension(120, 40));
        this.writeReview = new JButton("Write a review");
        this.writeReview.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WriteReviewWindow writeReviewWindow = new WriteReviewWindow();
                writeReviewWindow.singleDialogInformation();
            }
        });
        this.writeReview.setMaximumSize(new Dimension(120, 40));
        this.read = new JButton("Read a review");
        this.read.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadAReview readAReview= new ReadAReview();
                readAReview.singleDialogInformation();
            }
        });
        this.read.setMaximumSize(new Dimension(120, 40));
        this.func.add(this.inventory);
        this.info.add(Box.createVerticalStrut(10));
        this.func.add(this.writeReview);
        this.info.add(Box.createVerticalStrut(10));
        this.func.add(this.read);
        

        this.controlPanel = new JPanel();
        this.controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        // ********************** set game panel ************************ //
        this.gameView = new JPanel();
        this.games = new JLabel("Game");
        this.games.setFont(new java.awt.Font("Lucida Grande", 1, 13));
        this.buy = new JButton("Buy");
        this.buy.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyNewApp();
            }
        });
        this.delete = new JButton("Refund");
        this.delete.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refund();
            }
        });
        this.searchGames = new JButton("Search");
        this.searchGames.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchApps searchApps = new SearchApps();
                searchApps.searchPane();
            }
        });
        JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        subPanel.add(this.games);
        subPanel.add(Box.createRigidArea(new Dimension(150, 0)));
        subPanel.add(this.buy);
        subPanel.add(this.delete);
        subPanel.add(this.searchGames);

        // set up table
        String[] columnNames = {"GameID", "GameName", "Genre", "Achievement",
        "DeveloperID"};
        Object[][] data = {null, null, null, null, null}; // should be done by a query --
        this.gameTable = new JTable();
        this.gameTable.setModel(new DefaultTableModel(null, columnNames));
        // add table to pane
        this.gamePane = new JScrollPane(this.gameTable);
        this.gameTable.setFillsViewportHeight(true);

        this.gameView.setLayout(new BoxLayout(this.gameView, BoxLayout.Y_AXIS));
        this.gameView.add(subPanel);
        this.gameView.add(this.gamePane);

        // ********************** set other applications panel ************************
        // Todo if needed
        // ********************** set group panel ************************ //
        this.groupView = new JPanel();
        this.groups = new JLabel("Group");
        this.groups.setFont(new java.awt.Font("Lucida Grande", 1, 13));
        this.join = new JButton("Join");
        this.join.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinGroup();
            }
        });
        this.quit = new JButton("Quit");
        this.quit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitGroup();
            }
        });
        JPanel subGroupPanel = new JPanel();
        subGroupPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        subGroupPanel.add(this.groups);
        subGroupPanel.add(this.join);
        subGroupPanel.add(this.quit);

        // set up table
        String[] groupColumnNames = {"Group Name", "Member", "Tag"};
        Object[][] groupData = {null, null, null}; // should be done by a query --
        this.groupTable = new JTable();
        this.groupTable.setModel(new DefaultTableModel(null, groupColumnNames));
        // add table to pane
        this.groupPane = new JScrollPane(this.groupTable);
        this.groupTable.setFillsViewportHeight(true);

        this.groupView.setLayout(new BoxLayout(this.groupView, BoxLayout.Y_AXIS));
        this.groupView.add(subGroupPanel);
        this.groupView.add(this.groupPane);

    
        this.controlPanel.add(this.gameView);
        this.controlPanel.add(Box.createHorizontalStrut(10));
        this.controlPanel.add(this.groupView);
        // ************************** set big panel **************************//
        this.bigPanel = new JPanel(new BorderLayout());
        this.bigPanel.add(this.info, BorderLayout.WEST);
        this.bigPanel.add(this.func, BorderLayout.EAST);
        this.bigPanel.add(this.controlPanel, BorderLayout.SOUTH);
        this.add(bigPanel);
        this.setSize(new Dimension(800, 700));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private ResultSet performPlayerQuery() {
        return null;
    }

    private ResultSet getAllGameThePlayerHas() {
        return null;
    }

    private ResultSet getAllGroupThePlayerHas() {
        //try {
            return bank.getAllGroupThePlayerHas(Integer.toString(this.playerID));
        //} catch () {

        //}
    }

    private Object[][] readCurrentGames() {
        return null;
    }

    private Object[][] readCurrentGroup() {
        return null;
    }

    private void buyNewApp() {
        String appId = JOptionPane.showInputDialog(null, "Please enter the app ID you want to purchase");
        if (appId != null) {
            System.out.println(appId); // should perform query, and perform insert statement
            try {
                bank.buyGame(new PurchaseModel(Integer.toString(this.playerID), appId, java.time.LocalDate.now().toString()));
                JOptionPane.showMessageDialog(null, "successful");
            } catch (SQLException e) {
                if (e.getErrorCode() == 2291) {
                    noFound("Buy Not Found");
                } else if (e.getErrorCode() == 1) {
                    noFound("Buy Already Has");
                }
            }
        }
    }

    private void refund() {
        String appId = JOptionPane.showInputDialog(null, "Please enter the app ID you want to refund");
        if (appId != null) {
            System.out.println(appId); // should perform query, see if it is owned / not found. Perform delete statement
            // refundGame(this.playerID, appId);
            try {
                bank.refundGame(Integer.toString(this.playerID), appId);
                JOptionPane.showMessageDialog(null, "successful");
            } catch (IOException e) {
                noFound("Refund");
            }
            System.out.println("successful");
        }
    }

    private void joinGroup() {
        String gname = JOptionPane.showInputDialog(null, "Please enter the name of group you want to join");
        if (gname != null) {
            System.out.println(gname);
            try{
                bank.addSelfToGroup(new InGroupModel(gname, Integer.toString(this.playerID)));
                JOptionPane.showMessageDialog(null, "successful");
            } catch (SQLException e1) {
                if (e1.getErrorCode() == 1) {
                    noFound("Already Join");
                } else if (e1.getErrorCode() == 2291) {
                    noFound("Group Name");
                }
            }
        }
    }

    private void quitGroup() {
        String gname = JOptionPane.showInputDialog(null, "Please enter the name of group you want to quit");
        if (gname != null) {
            System.out.println(gname);
            try {
                bank.removeSelfFromGroup(gname, Integer.toString(this.playerID));
                JOptionPane.showMessageDialog(null, "successful");
            } catch (SQLException e2) {
                if (e2.getErrorCode() == 1) {
                    noFound("Already quit");
                } else if (e2.getErrorCode() == 2291) {
                    noFound("Group Name");
                }
            } catch (IOException e1) {
                noFound("Group Name");
            } catch (Exception e) {
                noFound("Already quit");
            }
        }
    }

    private void noFound(String type) { // use buy above functionss
        if (type == "Buy Not Found"){
            JOptionPane.showMessageDialog(null, "Sorry, we cannot find such an item in our database", "Not Found!", JOptionPane.ERROR_MESSAGE);
        } else if (type == "Buy Already Has") {
            JOptionPane.showMessageDialog(null, "Sorry, you cannot buy same item more than once", "You already have it!", JOptionPane.WARNING_MESSAGE);
        } else if (type == "Refund") {
            JOptionPane.showMessageDialog(null, "Sorry, we cannot find such an item in your purchases", "Not Found!", JOptionPane.ERROR_MESSAGE);
        } else if (type == "Group Name") {
            JOptionPane.showMessageDialog(null, "Sorry, we cannot find such an group", "Not Found!", JOptionPane.ERROR_MESSAGE);
        } else if (type == "Already Join") {
            JOptionPane.showMessageDialog(null, "Sorry, you have already in that group", "You already join!", JOptionPane.ERROR_MESSAGE);
        } else if (type == "Already quit") {
            JOptionPane.showMessageDialog(null, "Sorry, you cannot quit a group that you are not in", "You are not the member!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private BufferedImage chooseAvatar(int playerID) throws IOException{
        BufferedImage image = null;
        System.out.println(playerID);
        switch (playerID) {
            case 100:
                image = ImageIO.read(new File("ui/resource/gotcha.png"));
                break;
            case 200:
                image = ImageIO.read(new File("ui/resource/sportsman.png"));
                break;
            case 300:
                image = ImageIO.read(new File("ui/resource/motor.png"));
                break;
            case 400:
                image = ImageIO.read(new File("ui/resource/blackman.png"));
                break;
            case 500:
                image = ImageIO.read(new File("ui/resource/girl.png"));
                break;
        }
        return image;
    }
}