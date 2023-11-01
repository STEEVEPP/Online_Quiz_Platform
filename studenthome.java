import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class studenthome {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String roomNumber = JOptionPane.showInputDialog("Enter Room Number:");
                createAndShowGUI(roomNumber);
            }
        });
    }

    


    private static void createAndShowGUI(String roomNumber) {
        System.out.println(roomNumber);

        

        JFrame frame = new JFrame("Online Quiz Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // Background image for the panel
        ImageIcon background = new ImageIcon("index background.png");
        JLabel backgroundLabel = new JLabel(background);
        contentPanel.add(backgroundLabel);

        // Question label

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "password123A$");
            String query = "SELECT * FROM questions WHERE room_no = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, roomNumber);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String questionText = rs.getString("question");
                String optionA = rs.getString("option_1");
                String optionB = rs.getString("option_2");
                String optionC = rs.getString("option_3");
                String optionD = rs.getString("option_4");
                String correctOption = rs.getString("correct_answer");
                //System.out.println(questionText);
                //Question question = new Question(questionText, optionA, optionB, optionC, optionD, correctOption);
                //questions.add(question);
                JLabel questionLabel = new JLabel(questionText);
                contentPanel.add(questionLabel, BorderLayout.NORTH);
                
                ButtonGroup buttonGroup = new ButtonGroup();
                JRadioButton option1 = new JRadioButton(optionA);
                JRadioButton option2 = new JRadioButton(optionB);
                JRadioButton option3 = new JRadioButton(optionC);
                JRadioButton option4 = new JRadioButton(optionD);

                buttonGroup.add(option1);
                buttonGroup.add(option2);
                buttonGroup.add(option3);
                buttonGroup.add(option4);

                JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
                optionsPanel.add(option1);
                optionsPanel.add(option2);
                optionsPanel.add(option3);
                optionsPanel.add(option4);
                contentPanel.add(optionsPanel, BorderLayout.CENTER);

                ImageIcon nextIcon = new ImageIcon("Next.png"); // Replace with your icon image path
                JButton nextButton = new JButton("Next", nextIcon);
                nextButton.setBounds(995, 365, 200, 40);
                nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Add your registration logic here
                    //frame.dispose();
                    //Registerstudent.main(null);
    
            }
        });
            }
        
    
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //JLabel questionLabel = new JLabel(questionText);
        //contentPanel.add(questionLabel, BorderLayout.NORTH);

        // Radio buttons for options
        //ButtonGroup buttonGroup = new ButtonGroup();
        //JRadioButton option1 = new JRadioButton("Option A");
        //JRadioButton option2 = new JRadioButton("Option B");
        //JRadioButton option3 = new JRadioButton("Option C");
        //JRadioButton option4 = new JRadioButton("Option D");

        //buttonGroup.add(option1);
        //buttonGroup.add(option2);
        //buttonGroup.add(option3);
        //buttonGroup.add(option4);

        //JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        //optionsPanel.add(option1);
        //optionsPanel.add(option2);
        //optionsPanel.add(option3);
        //optionsPanel.add(option4);
        //contentPanel.add(optionsPanel, BorderLayout.CENTER);

        // Next button
        JButton nextButton = new JButton("Exit");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logic to move to the next question
                // You can implement your logic here
                // For simplicity, let's close the frame in this example
                frame.dispose();
            }
        });
        contentPanel.add(nextButton, BorderLayout.SOUTH);

        frame.add(contentPanel);
        frame.setVisible(true);
    }
}
