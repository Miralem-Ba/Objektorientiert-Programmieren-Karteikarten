package ch.csbe.guidemonstartion.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

public class QuestionWindowTest {

    @Test
    public void testShowImageFromFolder() {
        QuestionWindow questionWindow = QuestionWindow.getInstance();
        File testFolder = new File("C:\\Users\\banov\\OneDrive\\Desktop\\320Projekt\\AutoMarke"); // Den Pfad bitte durch den tatsächlichen ersetzen

        questionWindow.showImageFromFolder(testFolder);

        int expectedNumberOfFiles = 17; // Erwartete Anzahl von Dateien im Ordner
        int actualNumberOfFiles = questionWindow.getImageFiles().size();

        assertEquals(expectedNumberOfFiles, actualNumberOfFiles,
                "Anzahl der Bilddateien stimmt nicht mit der erwarteten Anzahl überein");
    }
}
