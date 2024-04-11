import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class landing {
    private JButton registerButton;
    private JButton loginButton;
    private JPanel landingpane;


    public landing() {
        super();
        JFrame frame = new JFrame("Landing Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(landingpane);
        frame.setMinimumSize(new Dimension(390, 320));


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationForm registrationForm = new RegistrationForm(new JFrame());
                registrationForm.setVisible(true);

            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmLogin login = new frmLogin(new JFrame());
                login.setVisible(true);
            }
        });
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        landing landing = new landing();
    }
}
