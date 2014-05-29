package menu.item.mask;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;


public class NotMaximumSupressionMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public NotMaximumSupressionMenuItem(final Window window) {
		super("Not Maximum");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Image result = window.getFocusedPanel().getImage().clone();
				int[][] derivationDirections = result.getDerivationDirections();
				result.borderWithNoMaximumsDeletion(derivationDirections);
				window.getUnfocusedPanel().setImage(result);
				window.repaint();
			}
		});
	}
}