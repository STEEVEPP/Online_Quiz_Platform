import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
//import loginstudent;

public class Quiz{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Create a JFrame with a background image
        JFrame frame = new JFrame("Icon Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 700);
        frame.setResizable(true);

        // Load the background image
        ImageIcon backgroundImage = new ImageIcon("index background.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        frame.setContentPane(backgroundLabel);
        frame.setLayout(new FlowLayout());

        // Create student, admin, and exit buttons
        ImageIcon studentIcon = new ImageIcon("index student.png");
        ImageIcon adminIcon = new ImageIcon("index admin.png");
        ImageIcon exitIcon = new ImageIcon("Close.png");

        JButton studentButton = new JButton(studentIcon);
        JButton adminButton = new JButton(adminIcon);
        JButton exitButton = new JButton(exitIcon);

        

        // Add action listeners to the buttons
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle student button click
                //JOptionPane.showMessageDialog(frame, "Student button clicked");
                //frame.dispose(); // Close the Quiz window
                //new loginstudent();
                frame.dispose(); // Close the current frame
                loginstudent.main(null);
                
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle admin button click
                JOptionPane.showMessageDialog(frame, "Admin button clicked");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle exit button click
                System.exit(0);
            }
        });

        // Add buttons to the frame
        frame.add(studentButton);
        frame.add(adminButton);
        frame.add(exitButton);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }
}
