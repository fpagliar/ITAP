package menu.item.mask;

import gui.DoubleArrayInputAction;
import gui.GenericDoubleInputWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JMenuItem;

import model.Image;

public class HisteresisThresholdMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public HisteresisThresholdMenuItem(final Window window) {
		super("Histeresis");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] labels = {"t1", "t2"};
				Double[] values = {30.0, 150.0};
				new GenericDoubleInputWindow(window, labels, values, new DoubleArrayInputAction() {
					
					public void performAction(Window window, Map<String, Double> inputs) {
						Image result = window.getFocusedPanel().getImage().clone();
						result.histeresisThreshold(inputs.get("t1"), inputs.get("t2"));
						window.getUnfocusedPanel().setImage(result);
						window.repaint();
					}
				});
			}
		});
	}
}