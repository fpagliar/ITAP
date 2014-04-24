package menu.item.operations.noise;

import gui.InputDoubleAction;
import gui.InputDoubleWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class RayleighNoiseMenuItem extends JMenuItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RayleighNoiseMenuItem(final Window window) {
		super("Rayleigh");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new InputDoubleWindow(window, "Epsilon", 1.0, new InputDoubleAction() {
					
					public void performAction(Window window, double input) {
						window.getUnfocusedPanel().setImage(window.getFocusedPanel().getImage().clone());
						window.getUnfocusedPanel().getImage().rayleighNoise(input);
						window.repaint();
					}
				});
			}
		});
	}
}