package menu.item.operations;

import gui.DoubleArrayInputAction;
import gui.GenericDoubleInputWindow;
import gui.Panel;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JMenuItem;

import model.Image;

public class IncreaseContrastMenuItem extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncreaseContrastMenuItem(final Window window) {
		super("Increase contrast");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] labels = {"r1", "r2", "y1", "y2"};
				Double[] values = {80.0, 200.0, 100.0, 220.0};
				new GenericDoubleInputWindow(window, labels, values, new DoubleArrayInputAction() {
					
					public void performAction(Window window, Map<String, Double> inputs) {
						Panel focused = window.getFocusedPanel();
						Panel unfocused = window.getUnfocusedPanel();
						Image result = focused.getImage().clone();
						result.contrast(inputs.get("r1"), inputs.get("r2"), inputs.get("y1"), inputs.get("y2"));
						unfocused.setImage(result);
						window.repaint();
					}
				});
			}
		});
	}
}
