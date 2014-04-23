package menu.item.operations;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class ToGrayscaleMenuItem extends JMenuItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ToGrayscaleMenuItem(final Window window) {
		super("To grayscale");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				window.getFocusedPanel().getImage().toGrayscale();
				window.repaint();
			}
		});
	}
}
