package gui;

import javax.swing.JMenuBar;

import menu.FileMenu;
import menu.item.SelectPanelMenuItem;

public class MainMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public MainMenu(Window window, Panel panel1, Panel panel2) {

		this.add(new FileMenu(window));
		this.add(new SelectPanelMenuItem(window, panel1));
		this.add(new SelectPanelMenuItem(window, panel2));
	}
}
