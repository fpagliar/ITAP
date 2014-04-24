package menu.item.analysis;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;
import utils.ColorUtilities;

public class HistogramMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public HistogramMenuItem(final Window window) {
		super("Create histogram");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Image grayscaleImage = window.getFocusedPanel().getImage()
						.clone();
				grayscaleImage.toGrayscale();
				Image histogram = ColorUtilities
						.generateHistogram(grayscaleImage);
				window.getUnfocusedPanel().setImage(histogram);
				window.repaint();
			}
		});
	}
}
