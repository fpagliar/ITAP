package menu.item.analysis;

import gui.InformationWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;

public class GlobalThresholdMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public GlobalThresholdMenuItem(final Window window) {
		super("Calculate global threshold");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Image image = window.getFocusedPanel().getImage().clone();
				double value = image.getGlobalThreshold();
				image.applyThreshold(value);
				
				window.getUnfocusedPanel().setImage(image);
				window.repaint();
				new InformationWindow("Threshold value: " + value);
			}
		});
	}
}

