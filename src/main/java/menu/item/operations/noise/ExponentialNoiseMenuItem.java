package menu.item.operations.noise;

import gui.InputDoubleAction;
import gui.InputDoubleWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class ExponentialNoiseMenuItem extends JMenuItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExponentialNoiseMenuItem(final Window window) {
		super("Exponential");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new InputDoubleWindow(window, "Mean", 0.05, new InputDoubleAction() {
					
					public void performAction(Window window, double input) {
						window.getUnfocusedPanel().setImage(window.getFocusedPanel().getImage().clone());
						window.getUnfocusedPanel().getImage().exponentialNoise(input);
						window.repaint();
					}
				});
			}
		});
	}
}
