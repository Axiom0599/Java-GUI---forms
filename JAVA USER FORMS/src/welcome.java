import javax.swing.*;
import java.awt.*;

public class welcome {
    private JPanel welcomepane;
    private JLabel welcome;


    public welcome() {
        super();
        JFrame home = new JFrame("Home page");
        home.setTitle("Welcome");
        home.setContentPane(welcomepane);
        home.setMinimumSize(new Dimension(420, 320));

        home.pack();
        home.setVisible(true);
    }


    public static void main(String[] args) {
        welcome home = new welcome();
    }
}

