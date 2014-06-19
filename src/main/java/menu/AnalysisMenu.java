package menu;

import gui.Window;

import javax.swing.JMenu;

import menu.item.analysis.GlobalThresholdMenuItem;
import menu.item.analysis.HarrisCornerDetectorMenuItem;
import menu.item.analysis.HistogramMenuItem;
import menu.item.analysis.ImageTrackingMenuItem;
import menu.item.analysis.OtsuThresholdMenuItem;
import menu.item.analysis.SiftMenuItem;
import menu.item.analysis.VideoTrackingMenuItem;

public class AnalysisMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public AnalysisMenu(Window window) {
		super("Analyze");
		this.setEnabled(true);

		this.add(new HistogramMenuItem(window));
		this.add(new GlobalThresholdMenuItem(window));
		this.add(new OtsuThresholdMenuItem(window));
		this.add(new ImageTrackingMenuItem(window));
		this.add(new VideoTrackingMenuItem(window));
		this.add(new HarrisCornerDetectorMenuItem(window));
		this.add(new SiftMenuItem(window));
	}
}