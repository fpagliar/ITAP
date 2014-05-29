package menu.item.mask;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;
import utils.MaskFactory;

public class LapacianWithCrossersMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public LapacianWithCrossersMenuItem(final Window window) {
		super("Laplacian with crossers");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Image result = window.getFocusedPanel().getImage().clone();
				result.applyMask(MaskFactory.laplacianMask());
				result.markZeroCrossers();
				window.getUnfocusedPanel().setImage(result);
				window.repaint();
			}
		});
	}
}