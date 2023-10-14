package ch.csbe.guidemonstartion;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ch.csbe.guidemonstartion.ui.MainWindow;

public class GUIDemonstartion {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		MainWindow mainWindow = MainWindow.getInstance();
		mainWindow.setVisible(true);
		
		

	}
}
