package menu.item.mask;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;
import utils.Mask;
import utils.MaskFactory;

public class LaplacianMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public LaplacianMenuItem(final Window window) {
		super("Laplacian");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Image result = window.getFocusedPanel().getImage().clone();
				Mask[] array = new Mask[1];
				array[0] = MaskFactory.laplacianMask();
				result.applyMasksAndSynth(array);
				window.getUnfocusedPanel().setImage(result);
				window.repaint();
			}
		});
	}
}