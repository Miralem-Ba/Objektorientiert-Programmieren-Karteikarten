package ch.csbe.guidemonstartion.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Represents the intermediate window that appears after clicking the "Start" button.
 * It provides an explanation about the significance of flashcards in the digital world and a "Next" button
 * to proceed to select the folder containing the learning materials.
 *
 * @author Miralem Banovi
 * @version 1.0
 */

public class StartExit extends JFrame implements ActionListener {
    private static final long serialVersionUID = 6575190792282698563L;
    private static StartExit instance;

    /**
     * Returns the instance of the StartExit class. If an instance does not exist,
     * it creates one and returns it.
     *
     * @return The instance of the StartExit class.
     */
    public static StartExit getInstance() {
        if (instance == null) {
            instance = new StartExit();
        }

        return instance;
    }

    private JButton startButton;
    private JButton backButton; // Renamed from baackButton

    private StartExit() {
        this.setSize(500, 500);

        // Create the top container panel
        JPanel topContainer = new JPanel(); // Create a new JPanel
        topContainer.setLayout(new BorderLayout()); // Set layout for the panel

        // Create and add the "Back" button to the top container
        backButton = new JButton("<"); // Renamed from baackButton
        backButton.addActionListener(this);
        topContainer.add(backButton, BorderLayout.LINE_START);

        // Add the top container to the frame
        this.add(topContainer, BorderLayout.PAGE_START);

        this.startButton = new JButton("Next");
        startButton.addActionListener(this);
        this.add(startButton, BorderLayout.PAGE_END);

        JTextArea textArea = new JTextArea();
        this.add(textArea);

        // Center Container
        JLabel explanationLabel = new JLabel("<html>In der heutigen digitalen Welt haben sich Lernkarten auch online weiterentwickelt,<br/>"
                + "was den Zugriff und die Organisation von Lernmaterialien weiter erleichtert.<br/>"
                + "Ob in Papierform oder digital, Lernkarten sind nach wie vor ein bewährtes Hilfsmittel,<br/>"
                + "um das Lernen effektiver und effizienter zu gestalten.");
        explanationLabel.setHorizontalAlignment(JLabel.CENTER);
        explanationLabel.setFont(explanationLabel.getFont().deriveFont(Font.BOLD, 17));
        this.add(explanationLabel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.startButton) {
            // Show the folder selection dialog and pass the current instance of StartExit
            OrdnerAuswahl.showFolderSelectionDialog();
            // Hide the StartExit window
            this.setVisible(false);
        } else if (event.getSource() == this.backButton) {
            MainWindow mainWindow = MainWindow.getInstance();
            mainWindow.setVisible(true);
            this.setVisible(false);
        }
    }
}
