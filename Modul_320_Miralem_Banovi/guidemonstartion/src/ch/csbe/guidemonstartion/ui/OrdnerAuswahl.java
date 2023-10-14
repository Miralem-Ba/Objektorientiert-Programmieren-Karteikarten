package ch.csbe.guidemonstartion.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Handles the selection of a folder where the learning materials are stored.
 * It displays a file selection dialog for the user to choose a folder.
 * After selection, the QuestionWindow is displayed with the chosen folder's contents.
 *
 * @author Miralem Banovi
 * @version 1.0
 */

public class OrdnerAuswahl {
    /**
     * Displays a file selection dialog for the user to choose a folder containing learning materials.
     * After selecting the folder, the QuestionWindow is displayed to show the contents of the chosen folder.
     */
    public static void showFolderSelectionDialog() {
        JFrame frame = new JFrame("Ordnerauswahl");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Create an instance of the class to use for the ActionListener
        OrdnerAuswahlActionListener actionListener = new OrdnerAuswahlActionListener();

        // Create the "Return" button
        JButton backButton = new JButton("<");
        backButton.addActionListener(actionListener); // Assign the ActionListener
        frame.add(backButton, BorderLayout.LINE_START);

        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
            System.out.println("Ausgewählter Ordner: " + selectedFolder.getAbsolutePath());

            // Show the QuestionWindow with the selected folder's contents
            QuestionWindow questionWindow = QuestionWindow.getInstance();
            questionWindow.setVisible(true);
            questionWindow.showImageFromFolder(selectedFolder); // Call this method to display the image

            // Hide the OrdnerAuswahl window
            frame.setVisible(false);
            frame.dispose();
        } else {
            System.out.println("Kein Ordner ausgewählt");
        }
    }

    private static class OrdnerAuswahlActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() instanceof JButton) {
                JButton button = (JButton) event.getSource();
                if ("<".equals(button.getText())) {
                    // Hide the current window (frame) instead of setting it invisible
                    button.getParent().getParent().getParent().setVisible(false);
                }
            }
        }
    }
}
