package menu.item.operations;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class NegativeMenuItem extends JMenuItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NegativeMenuItem(final Window window) {
		super("To negative");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				window.getFocusedPanel().getImage().negative();
				window.repaint();
			}
		});
	}
}
