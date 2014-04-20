package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ErrorWindow {
	public ErrorWindow(String message) {
		final JPanel panel = new JPanel();
		JOptionPane.showMessageDialog(panel, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}