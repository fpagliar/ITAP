package menu;

import gui.Window;

import javax.swing.JMenu;

import menu.item.analysis.GlobalThresholdMenuItem;
import menu.item.analysis.HistogramMenuItem;
import menu.item.analysis.ImageTrackingMenuItem;
import menu.item.analysis.OtsuThresholdMenuItem;

public class AnalysisMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public AnalysisMenu(Window window) {
		super("Analyze");
		this.setEnabled(true);

		this.add(new HistogramMenuItem(window));
		this.add(new GlobalThresholdMenuItem(window));
		this.add(new OtsuThresholdMenuItem(window));
		this.add(new ImageTrackingMenuItem(window));
	}
}