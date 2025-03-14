package librarymanagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import librarymanagement.DAO.AdminDAO;
import librarymanagement.Entity.Admin;


public class SignUpPage extends JFrame implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton signupButton;

    public SignUpPage() {
        setTitle("Sign Up");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(this);
        add(signupButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        AdminDAO adminDAO = new AdminDAO();
        adminDAO.saveAdmin(admin);

        JOptionPane.showMessageDialog(this, "Sign up successful!");
        this.dispose();
    }
}