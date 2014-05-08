package menu.item.operations;

import gui.IsotropicDiffusionWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class IsotropicDiffusionMenuItem extends JMenuItem {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public IsotropicDiffusionMenuItem(final Window window) {
		super("Isotropic Diffusion");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new IsotropicDiffusionWindow(window.getFocusedPanel().getImage());
			}
		});
	}
}
