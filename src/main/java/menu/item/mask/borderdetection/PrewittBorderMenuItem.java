package menu.item.mask.borderdetection;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;
import utils.MaskFactory;

public class PrewittBorderMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public PrewittBorderMenuItem(final Window window) {
		super("Prewitt");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Image result = window.getFocusedPanel().getImage().clone();
				result.applyMasksAndSynth(MaskFactory.prewittMask(), MaskFactory.prewittMask().turn().turn());
//				result.applyMasksAndSynth(MaskFactory.prewittBorderMask());

				window.getUnfocusedPanel().setImage(result);
				window.repaint();
			}
		});
	}
}
