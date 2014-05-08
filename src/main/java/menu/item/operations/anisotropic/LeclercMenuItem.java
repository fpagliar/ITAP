package menu.item.operations.anisotropic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.DiffusionWindow;
import gui.InputDoubleAction;
import gui.InputDoubleWindow;
import gui.Window;

import javax.swing.JMenuItem;

import model.BorderDetector;

public class LeclercMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public LeclercMenuItem(final Window window) {
		super("Leclerc");

		setEnabled(true);

		this.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {				
				new InputDoubleWindow(window, "Sigma", 10.0,
						new InputDoubleAction() {
					public void performAction(Window window, final double input) {
						new DiffusionWindow(
								window.getFocusedPanel().getImage(),
								new BorderDetector() {
									
									public double g(double x) {
										return Math.exp(-Math.pow(x, 2)
												/ Math.pow(input, 2));
									}
								});
					}
				});
			}
		});
	}
}
