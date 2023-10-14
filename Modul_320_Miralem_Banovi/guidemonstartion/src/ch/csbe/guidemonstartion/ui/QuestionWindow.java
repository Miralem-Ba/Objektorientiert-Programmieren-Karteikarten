package ch.csbe.guidemonstartion.ui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the main game window that displays images, text, and handles user interactions.
 * This class manages the game mechanics, scoring, and user inputs.
 *
 * @author Miralem Banovi
 * @version 1.0
 */

public class QuestionWindow extends JFrame implements ActionListener {
    private static final long serialVersionUID = -8429055363017236971L;
    private static QuestionWindow instance;

    private JButton checkButton;
    private JTextField textField;
    private JLabel imageLabel;
    private String[] supportedExtensions = {".jpg", ".png", ".txt"};
    private Timer scoreTimer;
    private String previousAnswer = ""; // Variable zum Speichern der vorherigen Antwort

    private List<File> imageFiles;
    private int currentImageIndex = 0;
    private int score = 0;
    public List<File> getImageFiles() {
        return imageFiles;
    }

    
    /**
     * Get the singleton instance of the QuestionWindow.
     *
     * @return The singleton instance of the QuestionWindow.
     */

    public static QuestionWindow getInstance() {
        if (instance == null) {
            instance = new QuestionWindow();
        }
        return instance;
    }

    private QuestionWindow() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ... (Buttons, Labels, etc. initialization)

        // Initialize the JPanel for the upper container
        JPanel upperContainer = new JPanel(new BorderLayout());
        this.add(upperContainer, BorderLayout.PAGE_START);

        // Initialize the imageLabel and center it within the upper container
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        upperContainer.add(imageLabel, BorderLayout.CENTER);

        // Initialize the Timer for score
        int timerDelay = 1000; // 1 second
        scoreTimer = new Timer(timerDelay, this);
        scoreTimer.start();

        // Initialize the JPanel for bottom container
        JPanel bottomContainer = new JPanel();
        bottomContainer.setLayout(new BorderLayout());
        this.add(bottomContainer, BorderLayout.PAGE_END);

        // Initialize the Check Button
        checkButton = new JButton("Check");
        checkButton.addActionListener(this);
        bottomContainer.add(checkButton, BorderLayout.LINE_END);

        // Initialize the Text Field
        textField = new JTextField();
        textField.addActionListener(this);
        bottomContainer.add(textField, BorderLayout.CENTER);
    }

    /**
     * Display images and text from the specified folder.
     *
     * @param folder The folder containing images and text files.
     */
    
    public void showImageFromFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null && files.length > 0) {
            imageFiles = new ArrayList<>();

            for (File file : files) {
                if (file.isFile()) {
                    for (String extension : supportedExtensions) {
                        if (file.getName().endsWith(extension)) {
                            imageFiles.add(file); // Add the eligible image file to the list
                            break;
                        }
                    }
                }
            }

            Collections.shuffle(imageFiles); // Shuffle the image list
            showNextImage();
        } else {
            System.out.println("Keine unterstützten Dateien im ausgewählten Ordner gefunden.");
        }
    }
    
    private void showNextImage() {
        if (currentImageIndex < imageFiles.size()) {
            File nextImageFile = imageFiles.get(currentImageIndex);

            if (nextImageFile.getName().endsWith(".txt")) {
                try {
                    String content = new String(Files.readAllBytes(nextImageFile.toPath()));
                    imageLabel.setText(content);
                    imageLabel.setIcon(null);
                    textField.setText(previousAnswer); // Fill the text field with the previous answer
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } 
            // Laden und Anzeigen des nächsten Bildes
            else if (nextImageFile.getName().endsWith(".jpg") || nextImageFile.getName().endsWith(".png")) {
                ImageIcon imageIcon = new ImageIcon(nextImageFile.getAbsolutePath());
                imageLabel.setIcon(imageIcon);
                imageLabel.setText(null);
                textField.setText(previousAnswer); // Fill the text field with the previous answer
            }
            
            else {
                showScore();
            }
        }
            
            }
        

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.checkButton) {
            String userGuess = textField.getText();
            if (currentImageIndex < imageFiles.size()) {
                String correctAnswer = removeExtension(imageFiles.get(currentImageIndex).getName());
                if (userGuess.equals(correctAnswer)) {
                    score++; // Increase the score if the answer is correct
                    currentImageIndex++; // Move to the next image
                    previousAnswer = ""; // Reset the previous answer
                    if (currentImageIndex < imageFiles.size()) {
                        showNextImage();
                    } else {
                        // Display the final score since all images have been shown
                        showScore();
                    }
                } else {
                    // Keep the current image displayed if the answer is incorrect
                    previousAnswer = userGuess; // Save the previous answer
                    textField.setText(""); // Clear the text field for the next try
                }
            }
        }
    }

    String removeExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex != -1) {
            return filename.substring(0, lastDotIndex);
        }
        return filename;
    }

    // ... (Other methods and event handling)

    /**
     * Display the final score and ask the user if they want to play again.
     * If yes, the game is restarted; if no, the application is closed.
     */
    
    private void showScore() {
        imageLabel.setIcon(null); // Remove the image
        JOptionPane.showMessageDialog(this, "Dein Score: " + score);
        
        // Anzeigen eines Fensters mit dem Score und der Möglichkeit, das Spiel neu zu starten
        int option = JOptionPane.showConfirmDialog(this, "Möchtest du das Spiel erneut starten?", "Spiel beenden", JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            // Wenn "Ja" gewählt wurde, den Score zurücksetzen und das Spiel neu starten
            score = 0;
            currentImageIndex = 0;
            showNextImage();
        } else {
            // Wenn "Nein" gewählt wurde, das Spiel beenden
            System.exit(0);
        }
    }
}