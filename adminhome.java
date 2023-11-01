import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class adminhome {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Admin Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 700);

        // Create a JPanel for the admin home page
        JPanel panel = new JPanel(null);
        panel.setOpaque(false);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("index background.png"); // Replace with your image path
        JLabel backgroundLabel = new JLabel(backgroundImage);
        frame.setContentPane(backgroundLabel);

        // Delete button with icon
        ImageIcon deleteIcon = new ImageIcon("delete Question.png"); // Replace with your icon image path
        JButton deleteButton = new JButton("Delete",deleteIcon);
        deleteButton.setBounds(100, 50, 200, 100);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for the question ID to delete
                String input = JOptionPane.showInputDialog(frame, "Enter the ID of the question to delete:");

                // Check if the input is valid
                if (input != null && !input.isEmpty()) {
                    try {
                        int questionId = Integer.parseInt(input);

                        // Create a SQL DELETE statement to delete the question with the specified ID
                        String query = "DELETE FROM questions WHERE question_id = " + questionId;

                        // Establish a connection to the database
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "password123A$");

                        // Create a Statement object to execute the query
                        Statement stmt = conn.createStatement();

                        // Execute the query and get the number of rows affected
                        int rowsAffected = stmt.executeUpdate(query);

                        // Close the Statement and Connection objects
                        stmt.close();
                        conn.close();

                        // Show a message to the user indicating success or failure
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "Question deleted successfully.");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Question not found.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid question ID.");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error deleting question: " + ex.getMessage());
                    }
                }
            }
        });

        ImageIcon displayIcon = new ImageIcon("display.png"); // Replace with your icon image path
        JButton displayButton = new JButton("display",displayIcon);
        displayButton.setBounds(450, 50, 200, 100);
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your delete question logic here
                frame.dispose();
                display.main(null);
            }
        });
        

        // Add button with icon
        ImageIcon addIcon = new ImageIcon("add new question.png"); // Replace with your icon image path
        JButton addButton = new JButton("Add", addIcon);
        addButton.setBounds(750, 50, 200, 100);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your add question logic here
                frame.dispose();
                addquestion.main(null);
            }
        });

        // Logout button with icon
        ImageIcon logoutIcon = new ImageIcon("Close.png"); // Replace with your icon image path
        JButton logoutButton = new JButton("Logout",logoutIcon);
        logoutButton.setBounds(1100, 50, 200, 100);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your logout logic here
                frame.dispose();
                Quiz.main(null);
            }
        });

        // Add components to the panel
        frame.add(deleteButton);
        frame.add(addButton);
        frame.add(logoutButton);
        frame.add(displayButton);

        // Add the panel to the frame
        frame.add(panel);

        frame.setVisible(true);
    }
}
