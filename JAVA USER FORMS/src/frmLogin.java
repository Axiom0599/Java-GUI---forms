import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class frmLogin extends JDialog{
    private JPanel loginpane;
    private JTextField login_name;
    private JButton forgotPasswordButton;
    private JButton exitButton;
    private JButton submitButton;
    private JButton clearButton;
    private JLabel name_label;
    private JLabel pass_label;
    private JPasswordField login_password;
    private JLabel header;
    private JButton changeColorButton;

    Connection con;
    PreparedStatement pst;

    private boolean isDarkMode = false;
    private void setColorTheme(Color backgroundColor, Color foregroundColor) {
        loginpane.setBackground(backgroundColor);
        loginpane.setForeground(new Color(18, 18, 18));
        login_name.setBackground(backgroundColor);
        login_name.setForeground(foregroundColor);
        login_password.setBackground(backgroundColor);
        login_password.setForeground(foregroundColor);
        name_label.setForeground(foregroundColor);
        header.setForeground(Color.blue);
        pass_label.setForeground(foregroundColor);
    }
    public void Connect() {
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/registrations","postgres", "0000");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public frmLogin(JFrame parent) {
        super(parent);
        setTitle("Login to your account");
        setLocationRelativeTo(parent);
        setContentPane(loginpane);
        setMinimumSize(new Dimension(420, 320));
        Connect();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = login_name.getText();
                String pass = new String(login_password.getPassword());;

                try {
                    pst=con.prepareStatement("select * from students where student_name=? AND student_password = ?");
                    pst.setString(1, uname);
                    pst.setString(2, pass);

                    ResultSet rs;
                    rs= pst.executeQuery();

                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "Login Successful!!");
                        dispose();
                       welcome home = new welcome();
                    }
                    else JOptionPane.showMessageDialog(null, "Invalid User");

                    rs.close();
                    pst.close();
                    con.close();
                    dispose();


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login_name.setText(" ");
                login_password.setText("");
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = login_name.getText();

                try {
                    pst = con.prepareStatement("SELECT student_password FROM students WHERE student_name=?");
                    pst.setString(1, uname);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        String password = rs.getString("student_password");
                        JOptionPane.showMessageDialog(null, "Your password is: " + password);

                    } else {
                        JOptionPane.showMessageDialog(null, "User not found");
                    }
                    rs.close();
                    pst.close();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
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

    public static void main(String[] args) {
        frmLogin myForm = new frmLogin(null);
    }

}


