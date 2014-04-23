package menu.item.operations;

import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;

public class AddImagesMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public AddImagesMenuItem(final Window window) {
		super("Add images");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Panel focused = window.getFocusedPanel();
				Panel unfocused = window.getUnfocusedPanel();
				Image result = focused.getImage().add(unfocused.getImage());
				window.getFocusedPanel().setImage(result);
				window.repaint();
			}
		});
	}
}
