package menu.item.operations.borderdetection;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;
import utils.MaskFactory;

public class SobelMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public SobelMenuItem(final Window window) {
		super("Sobel");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Image result = window.getFocusedPanel().getImage().clone();
				result.applyMasksAndSynth(MaskFactory.sobelMask());

				window.getUnfocusedPanel().setImage(result);
				window.repaint();
			}
		});
	}
}