package menu;

import gui.Window;

import javax.swing.JMenu;

import menu.item.analysis.HistogramMenuItem;

public class AnalysisMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public AnalysisMenu(Window window) {
		super("Analyze");
		this.setEnabled(true);

		this.add(new HistogramMenuItem(window));
	}
}