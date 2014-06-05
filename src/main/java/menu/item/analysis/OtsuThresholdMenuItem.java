package menu.item.analysis;

import gui.InformationWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;

public class OtsuThresholdMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public OtsuThresholdMenuItem(final Window window) {
		super("Calculate Otsu threshold");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {				
				Image other = window.getFocusedPanel().getImage().clone();
				double threshold = other.otsuThreshold();
				other.applyThreshold(threshold);
				window.getUnfocusedPanel().setImage(other);
				window.repaint();
				new InformationWindow("Calculated threshold:" + threshold);
			}
		});
	}
}
