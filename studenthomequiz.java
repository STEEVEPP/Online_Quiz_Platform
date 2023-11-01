import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class studenthomequiz {
    private ArrayList<Question> questions;
    private int currentIndex;

    public void StudentHome() {
        questions = new ArrayList<>();
        currentIndex = 0;
        loadQuestionsFromDatabase();
        createAndShowGUI();
    }

    private void loadQuestionsFromDatabase() {
        // Add your database connection and query logic here
        try {
            // Load questions from the database and populate the 'questions' list
            // You can use a loop to iterate through the result set and create Question objects
            // and add them to the 'questions' list.
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void createAndShowGUI() {
        if (currentIndex < questions.size()) {
            JFrame frame = new JFrame("Online Quiz Platform");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.setLayout(new BorderLayout());

            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());

            // Display the current question
            Question currentQuestion = questions.get(currentIndex);
            JLabel questionLabel = new JLabel("Question " + (currentIndex + 1) + ": " + currentQuestion.getQuestionText());
            contentPanel.add(questionLabel, BorderLayout.NORTH);

            // Radio buttons for options
            ButtonGroup buttonGroup = new ButtonGroup();
            JRadioButton option1 = new JRadioButton("Option A");
            JRadioButton option2 = new JRadioButton("Option B");
            JRadioButton option3 = new JRadioButton("Option C");
            JRadioButton option4 = new JRadioButton("Option D");

            // Populate radio button text with options from the current question
            option1.setText(currentQuestion.getOptionA());
            option2.setText(currentQuestion.getOptionB());
            option3.setText(currentQuestion.getOptionC());
            option4.setText(currentQuestion.getOptionD());

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

            // Next button
            JButton nextButton = new JButton("Next");
            nextButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Capture the user's answer
                    if (option1.isSelected()) {
                        questions.get(currentIndex).setUserAnswer("A");
                    } else if (option2.isSelected()) {
                        questions.get(currentIndex).setUserAnswer("B");
                    } else if (option3.isSelected()) {
                        questions.get(currentIndex).setUserAnswer("C");
                    } else if (option4.isSelected()) {
                        questions.get(currentIndex).setUserAnswer("D");
                    }

                    // Move to the next question
                    currentIndex++;

                    if (currentIndex < questions.size()) {
                        frame.dispose(); // Close the current frame
                        createAndShowGUI(); // Show the next question
                    } else {
                        frame.dispose(); // Close the current frame

                        // Calculate and display the user's score in a dialog box
                        int score = calculateScore();
                        JOptionPane.showMessageDialog(null, "Your score is: " + score + " out of " + questions.size());

                        // You can add more actions or display the score as desired
                    }
                }
            });
            contentPanel.add(nextButton, BorderLayout.SOUTH);

            frame.add(contentPanel);
            frame.setVisible(true);
        }
    }

    private int calculateScore() {
        int score = 0;
        for (Question question : questions) {
            if (question.getUserAnswer() != null && question.getUserAnswer().equals(question.getCorrectAnswer())) {
                score++;
            }
        }
        return score;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentHome();
            }
        });
    }
}
