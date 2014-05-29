package menu.item.mask.borderdetection;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;
import utils.MaskFactory;

public class RobertsBorderMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public RobertsBorderMenuItem(final Window window) {
		super("Roberts");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Image result = window.getFocusedPanel().getImage().clone();
				result.applyMasksAndSynth(MaskFactory.robertsMask(), MaskFactory.robertsMask().turn().turn());
//				result.applyMasksAndSynth(MaskFactory.robertsBorderMask());

				window.getUnfocusedPanel().setImage(result);
				window.repaint();
			}
		});
	}
}
