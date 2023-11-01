import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;


public class addquestion {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Add Question");
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
        JLabel roomno = new JLabel("Room No");
        roomno.setBounds(50, 50, 80, 20);

        // Name field
        JTextField roomnoField = new JTextField(20);
        roomnoField.setBounds(140, 50, 200, 30);

        // Email label
        JLabel question = new JLabel("Question");
        question.setBounds(50, 100, 80, 20);

        // Email field
        JTextField questionField = new JTextField(20);
        questionField.setBounds(140, 100, 200, 30);

        // Username label
        JLabel option1Label = new JLabel("Option 1");
        option1Label.setBounds(50, 150, 80, 20);

        // Username field
        JTextField option1Field = new JTextField(20);
        option1Field.setBounds(140, 150, 200, 30);

        // Password label
        JLabel option2Label = new JLabel("option 2");
        option2Label.setBounds(50, 200, 80, 20);

        // Password field
        JTextField option2Field = new JTextField(20);
        option2Field.setBounds(140, 200, 200, 30);

        JLabel option3Label = new JLabel("option 3");
        option3Label.setBounds(50, 250, 80, 20);

        // Password field
        JTextField option3Field = new JTextField(20);
        option3Field.setBounds(140, 250, 200, 30);

        JLabel option4Label = new JLabel("option 4");
        option4Label.setBounds(50, 300, 80, 20);

        // Password field
        JTextField option4Field = new JTextField(20);
        option4Field.setBounds(140, 300, 200, 30);

        JLabel option5Label = new JLabel("Correct_answer");
        option5Label.setBounds(50, 350, 80, 20);

        // Password field
        JTextField option5Field = new JTextField(20);
        option5Field.setBounds(140, 350, 200, 30);

        // Register button with icon
        ImageIcon registerIcon = new ImageIcon("register.png"); // Replace with your icon image path
        JButton registerButton = new JButton("Register", registerIcon);
        registerButton.setBounds(140, 550, 200, 40);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your registration logic here
                String roomno = roomnoField.getText();
                String question = questionField.getText();
                String option1 = option1Field.getText();
                String option2 = option2Field.getText();
                String option3 = option3Field.getText();
                String option4 = option4Field.getText();
                String correct_answer = option5Field.getText();
                //String password = new String(passwordField.getPassword());

                // Insert the data into the MySQL database
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "password123A$");
                    Statement st = con.createStatement();
                    String query = "INSERT INTO questions (room_no, question, option_1, option_2, option_3, option_4, correct_answer) VALUES ('" + roomno + "', '" + question + "', '" + option1 + "', '" + option2 + "','" + option3 + "','" + option4 + "','" + correct_answer + "')";
                    st.executeUpdate(query);
                    con.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                // Display a success message
                JOptionPane.showMessageDialog(frame, "Question Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        ImageIcon BackbuttonIcon = new ImageIcon("Back.png"); // Replace with your icon image path
        JButton Backbutton = new JButton("Previous", BackbuttonIcon);
        Backbutton.setBounds(140, 600, 200, 40);
        Backbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                adminhome.main(null);
        
            }
        });

        // Add components to the panel
        frame.add(roomno);
        frame.add(roomnoField);
        frame.add(question);
        frame.add(questionField);
        frame.add(option1Label);
        frame.add(option1Field);
        frame.add(option2Field);
        frame.add(option2Label);
        frame.add(option3Label);
        frame.add(option3Field);
        frame.add(option4Field);
        frame.add(option4Label);
        frame.add(option5Field);
        frame.add(option5Label);
        frame.add(registerButton);
        frame.add(Backbutton);

        // Add the panel to the background label
        backgroundLabel.add(panel);

        frame.setVisible(true);
    }
}
