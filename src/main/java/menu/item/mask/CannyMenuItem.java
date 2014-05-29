package menu.item.mask;

import gui.DoubleArrayInputAction;
import gui.GenericDoubleInputWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JMenuItem;

import utils.MaskFactory;
import model.Image;

public class CannyMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public CannyMenuItem(final Window window) {
		super("Canny");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] labels = {"t1", "t2"};
				Double[] values = {30.0, 150.0};
				new GenericDoubleInputWindow(window, labels, values, new DoubleArrayInputAction() {
					
					public void performAction(Window window, Map<String, Double> inputs) {
						Image result = window.getFocusedPanel().getImage().clone();
						int[][] derivationDirections = result.getDerivationDirections();
						result.applyMask(MaskFactory.gaussianMask());
						result.borderWithNoMaximumsDeletion(derivationDirections);
						result.histeresisThreshold(inputs.get("t1"), inputs.get("t2"));
						window.getUnfocusedPanel().setImage(result);
						window.repaint();
					}
				});
			}
		});
	}
}