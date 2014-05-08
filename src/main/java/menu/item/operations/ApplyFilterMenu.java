package menu.item.operations;

import gui.Window;

import javax.swing.JMenu;

import menu.item.operations.filters.GaussianFIlterMenuItem;
import menu.item.operations.filters.MeanFilterMenuItem;
import menu.item.operations.filters.MedianFilterMenuItem;

public class ApplyFilterMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public ApplyFilterMenu(Window window) {
		super("Filter");
		this.setEnabled(true);
		
		this.add(new MeanFilterMenuItem(window));
		this.add(new MedianFilterMenuItem(window));
		this.add(new GaussianFIlterMenuItem(window));
	}
}
