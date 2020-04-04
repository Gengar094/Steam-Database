// import sql.GameStoreDB;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
  
public class Login extends JFrame {

    public Login() {
        super();
    }

    public void main() {
        String[] choices = {"sniperElite", "goodPlayer", "badPlayer", "averagePlayer","gamerGirl"};
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new GridLayout(0,1));

        try {
            JPanel imgPanel = new JPanel();
            BufferedImage logo = ImageIO.read(new File("resource/icon.png"));
            JLabel picLabel = new JLabel(new ImageIcon(logo));
            //imgPanel.add(picLabel);
            outerPanel.add(picLabel);
        }catch (IOException e){
            System.out.println(e);
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(0,1));

        JTextField nameField = new JTextField();
        JTextField passField = new JTextField();
        loginPanel.add(new JLabel("Connection login"));
        loginPanel.add(new JLabel("Username: "));
        loginPanel.add(nameField);
        loginPanel.add(new JLabel("Password: "));
        loginPanel.add(passField);
        loginPanel.add(Box.createHorizontalStrut(15));
        JComboBox userSelect = new JComboBox(choices);
        loginPanel.add(new JLabel("Account"));
        loginPanel.add(userSelect);
        loginPanel.add(Box.createHorizontalStrut(15));

        userSelect.setSelectedIndex(3);
        nameField.setText("ora_CWL@stu");
        passField.setText("aSTUDENTNUM");

        outerPanel.add(loginPanel);

        Object[] option = {"Login", "Quit"};

        int result = JOptionPane.showOptionDialog(null, outerPanel,
                "Steam Database", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, option, null);
        if (result == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
        if (result == JOptionPane.OK_OPTION) {
            String val = userSelect.getSelectedItem().toString();
            System.out.println(val);
            PlayerWindow window = new PlayerWindow(toVal(val)); // need to check connection, see below
        }
        // if(result == JOptionPane.OK_OPTION){
        //     String userLevel = (String) userSelect.getSelectedItem();
        //     String user = nameField.getText();
        //     String pass = passField.getText();
        //     try{
        //         System.out.println("Test Conection...");
        //         // GameStoreDB.withConnection(user,pass,(con)->{});
        //         System.out.println("Test Success");
        //         // new AppFrameController(toVal(userLevel),user, pass).setVisible(true);
        //     }catch(SQLException e){
        //         JOptionPane.showMessageDialog(null, e + "\n\nPlease check your " +
        //                 "login credentials, and ensure you have created an ssh tunnel created \n" +
        //                 "   ssh -l UNIXID -L localhost:1522:dbhost.ugrad.cs.ubc.ca:1522 remote.ugrad.cs.ubc.ca", "Oops...", JOptionPane.ERROR_MESSAGE);
        //     }
        // }
    }
    private int toVal(String s){
        switch (s) {
            case "sniperElite":
                return 100;
            case "goodPlayer":
                return 200;
            case "badPlayer":
                return 300;
            case "averagePlayer":
                return 400;
            case "gamerGirl":
                return 500;
            default:
                return 0;
        }
    }
}