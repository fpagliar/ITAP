package menu.item.operations.filters;

import gui.GaussianFilterWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class GaussianFIlterMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public GaussianFIlterMenuItem(final Window window) {
		super("Gaussian");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new GaussianFilterWindow(window, "Side:", 3.0, "Sigma:", 1.0);
			}
		});

	}
}
