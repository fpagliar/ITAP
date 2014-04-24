package menu.item.operations;

import gui.InputDoubleAction;
import gui.InputDoubleWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.Image;

public class ApplyThresholdMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public ApplyThresholdMenuItem(final Window window) {
		super("Apply threshold");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new InputDoubleWindow(window, "Threshold", 255.0/2, new InputDoubleAction() {
					
					public void performAction(Window window, double input) {
						
						Image other = window.getFocusedPanel().getImage().clone();
						other.applyThreshold(input);
						window.getUnfocusedPanel().setImage(other);
						window.repaint();
					}
				});
			}
		});
	}
}
