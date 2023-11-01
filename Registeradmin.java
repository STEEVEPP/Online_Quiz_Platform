import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;


public class Registeradmin {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("admin Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 700);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("index background.png"); // Replace with your image path
        JLabel backgroundLabel = new JLabel(backgroundImage);
        frame.setContentPane(backgroundLabel);

        // Create a JPanel for the registration form
        JPanel panel = new JPanel(null);
        panel.setOpaque(false);

        // Name label
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 50, 80, 20);

        // Name field
        JTextField nameField = new JTextField(20);
        nameField.setBounds(140, 50, 200, 30);

        // Email label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 100, 80, 20);

        // Email field
        JTextField emailField = new JTextField(20);
        emailField.setBounds(140, 100, 200, 30);

        // Username label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50, 150, 80, 20);

        // Username field
        JTextField usernameField = new JTextField(20);
        usernameField.setBounds(140, 150, 200, 30);

        // Password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 200, 80, 20);

        // Password field
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(140, 200, 200, 30);

        // Register button with icon
        ImageIcon registerIcon = new ImageIcon("register.png"); // Replace with your icon image path
        JButton registerButton = new JButton("Register", registerIcon);
        registerButton.setBounds(140, 250, 200, 40);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your registration logic here
                String name = nameField.getText();
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Insert the data into the MySQL database
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "password123A$");
                    Statement st = con.createStatement();
                    String query = "INSERT INTO admin (name, email, username, password) VALUES ('" + name + "', '" + email + "', '" + username + "', '" + password + "')";
                    st.executeUpdate(query);
                    con.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                // Display a success message
                JOptionPane.showMessageDialog(frame, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        ImageIcon BackbuttonIcon = new ImageIcon("Back.png"); // Replace with your icon image path
        JButton Backbutton = new JButton("Previous", BackbuttonIcon);
        Backbutton.setBounds(140, 300, 200, 40);
        Backbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                loginadmin.main(null);
        
            }
        });

        // Add components to the panel
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(registerButton);
        frame.add(Backbutton);

        // Add the panel to the background label
        backgroundLabel.add(panel);

        frame.setVisible(true);
    }
}
