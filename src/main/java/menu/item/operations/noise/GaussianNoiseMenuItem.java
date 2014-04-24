package menu.item.operations.noise;

import gui.GaussianParameterWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class GaussianNoiseMenuItem extends JMenuItem {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public GaussianNoiseMenuItem(final Window window) {
		super("Gaussian");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new GaussianParameterWindow(window);
			}
		});
	}
}
