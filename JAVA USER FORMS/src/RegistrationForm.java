import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JDialog {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton submitButton;
    private JButton cancelButton;
    private JButton resetButton;
    private JPanel registerPanel;
    private JTextField textField2;
    private JLabel emailValidationLabel;
    private JButton changeColorButton;
    private JLabel register_name;
    private JLabel header;
    private JLabel pass_label;
    private JLabel email_lab;


    //checks for the validity of the email
    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    //dark mode settings
    private boolean isDarkMode = false;
    private void setColorTheme(Color backgroundColor, Color foregroundColor) {
        registerPanel.setBackground(backgroundColor);
        registerPanel.setForeground(new Color(18, 18, 18));
        textField1.setBackground(backgroundColor);
        textField1.setForeground(foregroundColor);
        passwordField1.setBackground(backgroundColor);
        passwordField1.setForeground(foregroundColor);
        textField2.setBackground(backgroundColor);
        textField2.setForeground(foregroundColor);
        emailValidationLabel.setForeground(Color.green);
        register_name.setForeground(foregroundColor);
        header.setForeground(Color.blue);
        pass_label.setForeground(foregroundColor);
        email_lab.setForeground(foregroundColor);
    }
    public RegistrationForm(JFrame parent){
        super(parent);
        setTitle("Register a new account");
        setLocationRelativeTo(parent);
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(450, 474));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();
                char[] password = passwordField1.getPassword();
                String passwordString = new String(password);
                String email = textField2.getText();

                if(name == null) {
                    JOptionPane.showMessageDialog(null, "Name cannot be null", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Validate password
                if (passwordString.contains(" ")) {
                    JOptionPane.showMessageDialog(null, "Password cannot contain spaces", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (password.length > 10 || password.length < 6) {
                    JOptionPane.showMessageDialog(null, "Password must be between 6 and 10 characters in length.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String upperCaseChars = "(.*[A-Z].*)";
                String lowerCaseChars = "(.*[a-z].*)";
                String numbers = "(.*[0-9].*)";
                String specialChars = "(.*[@, !,$,#].*$)";

                if (!passwordString.matches(upperCaseChars) || !passwordString.matches(lowerCaseChars)) {
                    JOptionPane.showMessageDialog(null, "Password must contain at least one uppercase and one lowercase letter", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (!passwordString.matches(numbers)) {
                    JOptionPane.showMessageDialog(null, "Password must contain at least one number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (!passwordString.matches(specialChars)) {
                    JOptionPane.showMessageDialog(null, "Password must contain at least one special character", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidEmail(email)) {
                    emailValidationLabel.setText("✘");
                    JOptionPane.showMessageDialog(null, "Please enter a valid email address", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    emailValidationLabel.setText("✔");
                }

                //update the database
                connect_to_postgres.connection(name, passwordString, email);
                JOptionPane.showMessageDialog(null, "Registration Successful.");

                textField1.setText("");
                passwordField1.setText("");
                textField2.setText("");
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(" ");
                passwordField1.setText("");
                textField2.setText("");
            }
        });

        setVisible(true);
        changeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isDarkMode) {
                    setColorTheme(Color.WHITE, new Color(18, 18, 18));
                    isDarkMode = false;
                } else {
                    setColorTheme(new Color(18, 18, 18), Color.WHITE);
                    isDarkMode = true;
                }
            }
        });
    }
    public static void main(String[] args){
        RegistrationForm myForm = new RegistrationForm(null);
    }
}











