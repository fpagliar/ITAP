package menu.item.analysis;

import gui.DoubleArrayInputAction;
import gui.GenericDoubleInputWindow;
import gui.Window;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JMenuItem;

import model.ColorImage.WeightedPoint;
import model.Image;

public class HarrisCornerDetectorMenuItem extends JMenuItem {
	private static final long serialVersionUID = 1L;

	public HarrisCornerDetectorMenuItem(final Window window) {
		super("Harris corner detector");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String[] labels = { "Mask size", "Sigma", "k", "Threshold" };
				// Double[] defaults = { 3.0, 0.25, 0.06, 70.0 };
				Double[] defaults = { 7.0, 2.0, 0.04, 70.0 };
				new GenericDoubleInputWindow(window, labels, defaults,
						new DoubleArrayInputAction() {

							public void performAction(Window window,
									Map<String, Double> inputs) {
								Image image = window.getFocusedPanel().getImage();
								List<Object> points = image.getHarrisMaxPoints(inputs.get("Mask size").intValue(), 
												inputs.get("Sigma"), inputs.get("k"), inputs.get("Threshold").intValue());
//								Image image = window.getFocusedPanel()
//										.getImage().getHarrisCIM(inputs.get("Mask size").intValue(), 
//												inputs.get("Sigma"), inputs.get("k"));
//								image.applyThreshold(inputs.get("Threshold"));
								System.out.println("size: " + points.size());
//								for(Point p : points)
//									image.setPixel(p.x, p.y, Color.RED);
								for(Object object : points) {
									WeightedPoint p = (WeightedPoint) object;
									image.setPixel(p.p.x, p.p.y, Color.RED);
								}
//								window.getUnfocusedPanel().setImage(image);
								window.repaint();
//								Image lx = image.clone();
//								lx.applyMask(MaskFactory.sobelMask());
//								Image ly = image.clone();
//								ly.applyMask(MaskFactory.sobelMask().turn()
//										.turn());
//
//								Image lx2 = lx.clone().multiply(lx);
//								for (int x = 0; x < lx2.getWidth(); x++)
//									for (int y = 0; y < lx2.getHeight(); y++) {
//										lx2.applyGaussianFilter(x, y, inputs
//												.get("Mask size").intValue(),
//												inputs.get("Sigma"));
//									}
//
//								Image ly2 = ly.clone().multiply(ly);
//								for (int x = 0; x < ly2.getWidth(); x++)
//									for (int y = 0; y < ly2.getHeight(); y++) {
//										ly2.applyGaussianFilter(x, y, inputs
//												.get("Mask size").intValue(),
//												inputs.get("Sigma"));
//									}
//
//								Image lxy = lx.clone().multiply(ly);
//								for (int x = 0; x < lxy.getWidth(); x++)
//									for (int y = 0; y < lxy.getHeight(); y++) {
//										lxy.applyGaussianFilter(x, y, inputs
//												.get("Mask size").intValue(),
//												inputs.get("Sigma"));
//									}
//
//								double k = inputs.get("k");
//								Image cim = new ColorImage(image.getHeight(),
//										image.getWidth(), image
//												.getImageFormat(), image
//												.getType());
//								lxy.getHarrisCIM();

								// image.applyHarrisCornerDetector(
								// inputs.get("Mask size").intValue(),
								// inputs.get("Sigma"),
								// inputs.get("Threshold"),
								// inputs.get("k"));
								// window.getUnfocusedPanel().setImage(image);
								// window.repaint();
							}
						});
			}
		});
	}
}