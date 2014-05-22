package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InformationWindow {
	public InformationWindow(String message) {
		final JPanel panel = new JPanel();
		JOptionPane.showMessageDialog(panel, message, "Info",
				JOptionPane.INFORMATION_MESSAGE);
	}
}