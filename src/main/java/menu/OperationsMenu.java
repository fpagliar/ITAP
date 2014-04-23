package menu;

import gui.Window;

import javax.swing.JMenu;

import menu.item.operations.AddImagesMenuItem;
import menu.item.operations.MultiplyImagesMenuItem;
import menu.item.operations.ScalarProductMenuItem;
import menu.item.operations.SubstractImagesMenuItem;

public class OperationsMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public OperationsMenu(Window window) {
		super("Operations");
		this.setEnabled(true);

		this.add(new AddImagesMenuItem(window));
		this.add(new SubstractImagesMenuItem(window));
		this.add(new MultiplyImagesMenuItem(window));
		this.add(new ScalarProductMenuItem(window));
	}
}
