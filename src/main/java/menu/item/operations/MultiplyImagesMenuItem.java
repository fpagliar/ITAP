package menu.item.operations;

import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;

public class MultiplyImagesMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public MultiplyImagesMenuItem(final Window window) {
		super("Multiply images");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Panel focused = window.getFocusedPanel();
				Panel unfocused = window.getUnfocusedPanel();
				Image result = focused.getImage().multiply(unfocused.getImage());
				window.getFocusedPanel().setImage(result);
				window.repaint();
			}
		});
	}
}
