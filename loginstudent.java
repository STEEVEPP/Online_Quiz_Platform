import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginstudent {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Sign In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 700);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("loginbackground.png"); // Replace with your image path
        JLabel backgroundLabel = new JLabel(backgroundImage);
        frame.setContentPane(backgroundLabel);

        // Create a JPanel for the sign-in form
        JPanel panel = new JPanel(null);
        panel.setOpaque(false);

        // Username label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(1055, 125, 80, 20);

        // Username field
        JTextField usernameField = new JTextField(20);
        usernameField.setBounds(1012, 150, 170, 30);

        // Password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(1055, 185, 80, 20);

        // Password field
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(1012, 215, 170, 30);

        JLabel roomid = new JLabel("Room ID");
        roomid.setBounds(1060, 250, 80, 20);

        // Password field
        JTextField roomid1 = new JTextField(20);
        roomid1.setBounds(1012, 270, 170, 30);

        // Login button with icon
        ImageIcon loginIcon = new ImageIcon("login.png"); // Replace with your icon image path
        JButton loginButton = new JButton("Login", loginIcon);
        loginButton.setBounds(995,315, 200, 40);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your login logic here
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String roomid = roomid1.getText();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "password123A$");
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM student WHERE username='" + username + "' AND password='" + password + "'");
                    if (rs.next()) {
                        // If there is a match, navigate to the studenthome page
                        frame.dispose();
                        studenthome.main(null);
                    } else {
                        // If there is no match, display an error message
                        JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    con.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                    }
        });

        // Register button with icon
        ImageIcon registerIcon = new ImageIcon("register.png"); // Replace with your icon image path
        JButton registerButton = new JButton("Register", registerIcon);
        registerButton.setBounds(995, 365, 200, 40);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your registration logic here
                frame.dispose();
                Registerstudent.main(null);
            }
        });

        // Add components to the panel
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(registerButton);
        frame.add(roomid);
        frame.add(roomid1);

        // Add the panel to the background label
        backgroundLabel.add(panel);

        frame.setVisible(true);
    }

    public void setVisible(boolean b) {
    }


    
}
