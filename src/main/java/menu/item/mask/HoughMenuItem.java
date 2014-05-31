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
import model.MaxSynth;

public class HoughMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public HoughMenuItem(final Window window) {
		super("Hough");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] labels = {"tita granularity", "ro granularity", "threshold"};
				Double[] values = {20.0, 20.0, 20.0};
				new GenericDoubleInputWindow(window, labels, values, new DoubleArrayInputAction() {
					
					public void performAction(Window window, Map<String, Double> inputs) {
						Image first = window.getFocusedPanel().getImage().clone();
						Image second = window.getFocusedPanel().getImage().clone();
						Image third = window.getFocusedPanel().getImage().clone();

						first.applyMask(MaskFactory.gaussianMask(0.5));
						second.applyMask(MaskFactory.gaussianMask(1));
						third.applyMask(MaskFactory.gaussianMask(10));

						int[][] derivationDirections1 = first.getDerivationDirections();
						int[][] derivationDirections2 = second.getDerivationDirections();
						int[][] derivationDirections3 = third.getDerivationDirections();

						first.borderWithNoMaximumsDeletion(derivationDirections1);
						second.borderWithNoMaximumsDeletion(derivationDirections2);
						third.borderWithNoMaximumsDeletion(derivationDirections3);

						first.histeresisThreshold(30, 150);
						second.histeresisThreshold(30, 150);
						third.histeresisThreshold(30, 150);
						
						first.synthesize(new MaxSynth(), second, third);

						first.applyHough(inputs.get("tita granularity").intValue(), inputs.get("ro granularity").intValue(), inputs.get("threshold"));
						window.getUnfocusedPanel().setImage(first);
						window.repaint();
					}
				});
			}
		});
	}
}