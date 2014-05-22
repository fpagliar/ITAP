package gui;

import javax.swing.JMenuBar;

import menu.AnalysisMenu;
import menu.FileMenu;
import menu.MaskMenu;
import menu.OperationsMenu;
import menu.item.file.MouseCoordinatesLabel;
import menu.item.file.SelectPanelMenuItem;

public class MainMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public MainMenu(Window window, Panel panel1, Panel panel2) {

		this.add(new FileMenu(window));
		this.add(new OperationsMenu(window));
		this.add(new MaskMenu(window));
		this.add(new AnalysisMenu(window));
		this.add(new SelectPanelMenuItem(window, panel1));
		this.add(new SelectPanelMenuItem(window, panel2));
		this.add(new MouseCoordinatesLabel(panel1, panel2));
	}
}
