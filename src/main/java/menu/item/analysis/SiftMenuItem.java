package menu.item.analysis;

import gui.InputDoubleAction;
import gui.InputDoubleWindow;
import gui.SiftWindow;
import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;

import model.Image;
import mpi.cbg.fly.Feature;
import mpi.cbg.fly.SIFT;


public class SiftMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public SiftMenuItem(final Window window) {
		super("Sift");

		setEnabled(true);

		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new InputDoubleWindow(window, "threshold", 0.1, new InputDoubleAction() {
					
					public void performAction(Window window, double input) {
						Image image1 = window.getFocusedPanel().getImage().clone();
						Image image2 = window.getUnfocusedPanel().getImage().clone();
						
						Vector<Feature> f1 = SIFT.getFeatures(image1.getImage());
						Vector<Feature> f2 = SIFT.getFeatures(image2.getImage());
						
						Map<String, List<Feature>> hits = analyzeFeatures(f1, f2, input);
						image1.detectFeatures(hits.get("f1"));
						image2.detectFeatures(hits.get("f2"));
						System.out.println("Hits:" + hits.get("f1").size());
						new SiftWindow(image1, image2, hits);
						
					}
				});
			}
		});
	}
	
	protected Map<String, List<Feature>> analyzeFeatures(Vector<Feature> f1, Vector<Feature> f2, double threshold) {
		Map<String, List<Feature>> selected = new HashMap<String, List<Feature>>();
		selected.put("f1", new ArrayList<Feature>());
		selected.put("f2", new ArrayList<Feature>());
		for (Feature feature : f1) {
			for (Feature inner : f2) {
				if (feature.descriptorDistance(inner) < threshold) {
					selected.get("f1").add(feature);
					selected.get("f2").add(inner);
				}
			}
		}
		return selected;
	}
}