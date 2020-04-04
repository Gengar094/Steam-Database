import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ModifyWindow extends JFrame{

    public JComboBox box;
    public JPanel pane;
    public JTextField textField;

    public ModifyWindow() {
        super("Modification");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pane = new JPanel();
        this.pane.setLayout(new BoxLayout (this.pane, BoxLayout.X_AXIS));
        Object[] options = {"Username", "email", "city", "conutry"};
        this.box = new JComboBox(options);
        this.box.setMaximumSize(new Dimension(100, 40));
        this.textField = new JTextField("Modify to ...");
        this.textField.setMaximumSize(new Dimension(500,40));
        this.pane.add(box);
        
        this.pane.add(this.textField);
        add(this.pane);
        setSize(new Dimension(500, 200));
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

	public void modifyInfo() {
        // get the text and perform query
	}

}
