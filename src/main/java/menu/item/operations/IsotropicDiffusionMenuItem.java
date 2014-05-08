package menu.item.operations;

import gui.DiffusionWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.BorderDetector;

public class IsotropicDiffusionMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public IsotropicDiffusionMenuItem(final Window window) {
		super("Isotropic Diffusion");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new DiffusionWindow(window.getFocusedPanel().getImage(),
						new BorderDetector() {

							public double g(double x) {
								// TODO Auto-generated method stub
								return 1;
							}
						});
			}
		});
	}
}
