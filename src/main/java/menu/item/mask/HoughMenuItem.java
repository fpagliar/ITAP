package menu.item.mask;

import gui.DoubleArrayInputAction;
import gui.GenericDoubleInputWindow;
import gui.Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenuItem;

import model.Image;
import model.MaxSynth;
import utils.Line;
import utils.MaskFactory;

public class HoughMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public HoughMenuItem(final Window window) {
		super("Hough");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] labels = { "tita granularity", "ro granularity",
						"threshold", "total lines" };
				Double[] values = { 20.0, 20.0, 20.0, 10.0 };
				new GenericDoubleInputWindow(window, labels, values,
						new DoubleArrayInputAction() {

							public void performAction(Window window,
									Map<String, Double> inputs) {
								Image first = window.getFocusedPanel()
										.getImage().clone();
//								Image second = window.getFocusedPanel()
//										.getImage().clone();
//								Image third = window.getFocusedPanel()
//										.getImage().clone();

//								first.applyMask(MaskFactory.gaussianMask(0.5));
//								second.applyMask(MaskFactory.gaussianMask(1));
//								third.applyMask(MaskFactory.gaussianMask(10));

								int[][] derivationDirections1 = first
										.getDerivationDirections();
//								int[][] derivationDirections2 = second
//										.getDerivationDirections();
//								int[][] derivationDirections3 = third
//										.getDerivationDirections();

								first.borderWithNoMaximumsDeletion(derivationDirections1);
//								second.borderWithNoMaximumsDeletion(derivationDirections2);
//								third.borderWithNoMaximumsDeletion(derivationDirections3);

								first.histeresisThreshold(30, 150);
//								second.histeresisThreshold(30, 150);
//								third.histeresisThreshold(30, 150);

//								first.synthesize(new MaxSynth(), second, third);

								Image withLines = first.clone();
								HashMap<Double, Double> roTitas = first
										.applyHough(
												inputs.get("tita granularity")
														.intValue(), inputs
														.get("ro granularity")
														.intValue(), inputs
														.get("threshold"),
												inputs.get("total lines")
														.intValue());
								window.getUnfocusedPanel().setImage(withLines);
								window.getUnfocusedPanel().lines = new ArrayList<Line>();
								window.repaint();
								Graphics g = window.getUnfocusedPanel().getGraphics();
								g.setColor(Color.RED);

								for (Double ro : roTitas.keySet()){
//									drawLine(roTitas.get(ro), ro, threshold);
//									int y0 = (int) ((ro - 0 * Math.cos(roTitas.get(ro))) / Math.sin(roTitas.get(ro)));
//									int y1 = (int) ((ro - 1 * Math.cos(roTitas.get(ro))) / Math.sin(roTitas.get(ro)));
//									System.out.println("Drawing: y0 - " +  y0 + " y1 - " + y1);
									try{
										window.getUnfocusedPanel().lines.add(new Line(roTitas.get(ro), ro, withLines.getWidth(), withLines.getHeight()));
									} catch(Exception e){}
//									g.drawLine(0, 0, 100, 100);
								}
//								window.getUnfocusedPanel().paintComponent(g);
//								withLines.drawHoughLines(roTitas,
//										inputs.get("threshold"));
							}
						});
			}
		});
	}
}