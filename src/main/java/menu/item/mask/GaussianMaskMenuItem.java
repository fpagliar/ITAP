package menu.item.mask;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;
import utils.MaskFactory;

public class GaussianMaskMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public GaussianMaskMenuItem(final Window window) {
		super("Gaussian");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Image result = window.getFocusedPanel().getImage().clone();
				result.applyMask(MaskFactory.gaussianMask());
				window.getUnfocusedPanel().setImage(result);
				window.repaint();
			}
		});
	}
}