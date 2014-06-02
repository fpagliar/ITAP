package menu.item.mask;

import gui.DoubleArrayInputAction;
import gui.GenericDoubleInputWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

import javax.swing.JMenuItem;

import model.Channel.Point3D;
import model.Image;
import model.MaxSynth;
import utils.MaskFactory;

public class HoughCirclesMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public HoughCirclesMenuItem(final Window window) {
		super("Hough circles");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] labels = { "a granularity", "b granularity", "r granularity",
						"threshold", "total circles" };
				Double[] values = { 20.0, 20.0, 10.0, 20.0, 5.0 };
				new GenericDoubleInputWindow(window, labels, values,
						new DoubleArrayInputAction() {

							public void performAction(Window window,
									Map<String, Double> inputs) {
								Image first = window.getFocusedPanel()
										.getImage().clone();
								Image second = window.getFocusedPanel()
										.getImage().clone();
								Image third = window.getFocusedPanel()
										.getImage().clone();

								first.applyMask(MaskFactory.gaussianMask(0.5));
								second.applyMask(MaskFactory.gaussianMask(1));
								third.applyMask(MaskFactory.gaussianMask(10));

								int[][] derivationDirections1 = first
										.getDerivationDirections();
								int[][] derivationDirections2 = second
										.getDerivationDirections();
								int[][] derivationDirections3 = third
										.getDerivationDirections();

								first.borderWithNoMaximumsDeletion(derivationDirections1);
								second.borderWithNoMaximumsDeletion(derivationDirections2);
								third.borderWithNoMaximumsDeletion(derivationDirections3);

								first.histeresisThreshold(30, 150);
								second.histeresisThreshold(30, 150);
								third.histeresisThreshold(30, 150);

								first.synthesize(new MaxSynth(), second, third);

								Image withLines = first.clone();
								Set<Point3D> circles = first.applyCircleHough(inputs.get("a granularity").intValue(), 
										inputs.get("b granularity").intValue(), inputs.get("r granularity").intValue(), 
										inputs.get("threshold"), inputs.get("total circles").intValue());
								withLines.drawHoughCircles(circles, inputs.get("threshold"));
//								HashMap<Double, Double> roTitas = first
//										.applyHough(
//												inputs.get("tita granularity")
//														.intValue(), inputs
//														.get("ro granularity")
//														.intValue(), inputs
//														.get("threshold"),
//												inputs.get("total lines")
//														.intValue());
//								withLines.drawHoughLines(roTitas,
//										inputs.get("threshold"));
								window.getUnfocusedPanel().setImage(withLines);
								window.repaint();
							}
						});
			}
		});
	}
}