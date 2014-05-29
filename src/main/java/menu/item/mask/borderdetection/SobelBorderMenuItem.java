package menu.item.mask.borderdetection;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;
import utils.MaskFactory;

public class SobelBorderMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public SobelBorderMenuItem(final Window window) {
		super("Sobel");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Image result = window.getFocusedPanel().getImage().clone();
//				result.applyMasksAndSynth(MaskFactory.sobelBorderMask());
				result.applyMasksAndSynth(MaskFactory.sobelMask(), MaskFactory.sobelMask().turn().turn());

				window.getUnfocusedPanel().setImage(result);
				window.repaint();
			}
		});
	}
}