package menu.item.operations.noise;

import gui.SaltAndPeperParameterWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class SaltAndPepperMenuItem extends JMenuItem {
	private static final long serialVersionUID = 1L;

	public SaltAndPepperMenuItem(final Window window) {
		super("Salt and pepper");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new SaltAndPeperParameterWindow(window);
			}

		});
	}
}
