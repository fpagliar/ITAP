package menu.item.analysis;

import gui.DoubleArrayInputAction;
import gui.GenericDoubleInputWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JMenuItem;

import model.Image;

public class HarrisCornerDetectorMenuItem extends JMenuItem {
	private static final long serialVersionUID = 1L;

	public HarrisCornerDetectorMenuItem(final Window window) {
		super("Harris corner detector");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] labels = { "Mask size", "Sigma", "k", "Threshold" };
				Double[] defaults = { 3.0, 0.25, 0.06, 70.0 };
				new GenericDoubleInputWindow(window, labels, defaults,
						new DoubleArrayInputAction() {

							public void performAction(Window window,
									Map<String, Double> inputs) {
								Image image = window.getFocusedPanel()
										.getImage().clone();
								image.applyHarrisCornerDetector(
										inputs.get("Mask size").intValue(),
										inputs.get("Sigma"),
										inputs.get("Threshold"),
										inputs.get("k"));
								window.getUnfocusedPanel().setImage(image);
								window.repaint();

							}
						});
			}
		});
	}
}