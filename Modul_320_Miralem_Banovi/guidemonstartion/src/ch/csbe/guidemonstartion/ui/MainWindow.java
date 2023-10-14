/**
 * This package contains classes related to the user interface of the application.
 */
package ch.csbe.guidemonstartion.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Represents the main window of the application. It displays a welcoming message and a "Start" button,
 * allowing the user to initiate the game. When the button is clicked, it opens the "StartExit" window.
 *
 * @author Miralem Banovi
 * @version 1.0
 */

public class MainWindow extends JFrame implements ActionListener {
    private static final long serialVersionUID = 6575190792282698563L;
    private static MainWindow instance;

    /**
     * Returns the instance of the MainWindow class. If an instance does not exist,
     * it creates one and returns it.
     *
     * @return The instance of the MainWindow class.
     */
    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }

        return instance;
    }

    private JButton testButton;

    private MainWindow() {
        this.setSize(500, 500);

        this.testButton = new JButton("Start");
        testButton.addActionListener(this);
        this.add(testButton, BorderLayout.PAGE_END);

        JTextArea textArea = new JTextArea();
        this.add(textArea);

        // Center Container
        JLabel questionLabel = new JLabel(" Welcome to my project ");
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setFont(questionLabel.getFont().deriveFont(Font.BOLD, 20));
        this.add(questionLabel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.testButton) {
            StartExit startExitMenu = StartExit.getInstance();
            startExitMenu.setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        MainWindow mainWindow = MainWindow.getInstance();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
    }
}
