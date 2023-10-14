package ch.csbe.guidemonstartion.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Represents the window where the user can view their score and answer questions.
 * This class handles the display of the score, questions, and user input for answers.
 *
 * @author Miralem Banovi
 * @version 1.0
 */

public class ScoreExit extends JFrame implements ActionListener {
    private static final long serialVersionUID = -8429055363017236971L;
    private static ScoreExit instance;

    /**
     * Get the singleton instance of the ScoreExit window.
     *
     * @return The singleton instance of the ScoreExit window.
     */
    public static ScoreExit getInstance() {
        if (instance == null) {
            instance = new ScoreExit();
        }

        return instance;
    }

    private JButton backButton;
    private JButton checkButton;
    private JTextField textField;

    private ScoreExit() {
        this.setSize(500, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Top Container
        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BorderLayout());
        this.add(topContainer, BorderLayout.PAGE_START);

        this.backButton = new JButton("<");
        this.backButton.addActionListener(this);
        topContainer.add(this.backButton, BorderLayout.LINE_START);

        JLabel scoreLabel = new JLabel("Score: 0");
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        topContainer.add(scoreLabel, BorderLayout.CENTER);

        // Center Container
        JLabel questionLabel = new JLabel("When was your mama Born?");
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setFont(questionLabel.getFont().deriveFont(Font.BOLD, 30));
        this.add(questionLabel, BorderLayout.CENTER);

        // Bottom Center
        JPanel bottomContainer = new JPanel();
        bottomContainer.setLayout(new BorderLayout());
        this.add(bottomContainer, BorderLayout.PAGE_END);

        this.checkButton = new JButton("Check");
        this.checkButton.addActionListener(this);
        bottomContainer.add(checkButton, BorderLayout.LINE_END);

        this.textField = new JTextField();
        this.textField.addActionListener(this);
        bottomContainer.add(textField, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.backButton) {
            StartExit startexitmenu = StartExit.getInstance();
            startexitmenu.setVisible(true);
            this.setVisible(false);
        } else if (event.getSource() == this.checkButton || event.getSource() == this.textField) {
            System.out.println("Check!");
        }
    }
}
